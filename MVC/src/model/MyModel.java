package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import comperators.StateCostComparator;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * <h1>  Model Interface <h1>
 * This class is doing all calculations touch data
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public class MyModel implements Model{

	Controller controller;
	HashMap<String, Maze3d> mazeInFile;
	ExecutorService threadpool;
	
	public MyModel(Controller controller) {
		this.controller = controller;
		this.mazeInFile = new HashMap<String, Maze3d>();
		this.threadpool = Executors.newFixedThreadPool(10);  
	}

	@Override
	public void generateMaze3d(int x, int y, int z, String generate,String name) {
		threadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				Maze3dGenerator mg = null;
				if(generate.equals("MyMaze3dGenerator")==true)
					mg = new MyMaze3dGenerator(x,y,z);
				else
					mg = new SimpleMaze3dGenerator(x,y,z);
				
				controller.setMaze3d(mg.getMaze(),name);
			}
		});
	}
	

	@Override
	public void crossBySection(Maze3d maze, String name, int section, char typeSection) {
		if(typeSection=='x')
			controller.crossSection(maze.getCrossSectionByX(section), typeSection, name , section);
		else if(typeSection=='y')
			controller.crossSection(maze.getCrossSectionByY(section), typeSection, name , section);
		else if(typeSection=='z')
			controller.crossSection(maze.getCrossSectionByZ(section), typeSection, name , section);
	}

	
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void saveMaze(Maze3d maze, String name, String fileName) {
		MyCompressorOutputStream outFile;
		try {
			outFile = new MyCompressorOutputStream(new FileOutputStream(fileName));
			outFile.write(maze.toByteArray());
			mazeInFile.put(fileName, maze);
			String s = "file "+fileName+" is ready";
			controller.printStr(s);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadMaze(String name , String fileName) {
		MyDecompressorInputStream inFile;
		try {
			inFile = new MyDecompressorInputStream(new FileInputStream(fileName));
			byte[] b = new byte[4096];
			if(mazeInFile.containsKey(fileName)==true)
			{
				if(inFile.read(b)!=-1)
				{
					Maze3d maze = new Maze3d(b);
					controller.loadMaze(maze,name);
				}
				else
					controller.printStr("Can't load maze");	
			}
			else
				controller.printStr("File " + fileName + " is not exist!");	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mazeSize(Maze3d maze, String name) {
		String s = "maze size " + name + " is " +(maze.getXSize()*maze.getYSize()*maze.getZSize()*Integer.SIZE)/8+ " bytes";
		controller.printStr(s);
	}

	@Override
	public void fileSize(String[] args) {
		if(mazeInFile.containsKey(args[2])==true)
		{
			File f = new File(args[2]);
			controller.printStr("The size of the maze in the file is " +f.length()+ " bytes");
		}
		else
			controller.printStr("File " + args[2] + " is not exist!");
	}

	@Override
	public void solveMaze(String[] args, Maze3d maze) {
		threadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				Searchable<Position> s = new Maze3dDomain(maze);
				if(args.length>=3 && args.length<5)
				{
					if(args[2].equals("BFS")==true)
					{
						BFS<Position> solve = new BFS<Position>(new StateCostComparator<Position>());
						controller.setSolution(solve.search(s).getSol(),args[1]);
						controller.printStr("solution for " +args[1]+ " is ready");
					}
					else
						controller.printStr("Algorithm is not exist");
				}
				else if(args.length>=5)
				{
					if((args[2]+" "+args[3]+" "+args[4]).equals("Astar Air Distance")==true)
					{
						BFS<Position> solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeAirDistance(s));
						controller.setSolution(solve.search(s).getSol(),args[1]);
						controller.printStr("solution for " +args[1]+ " is ready");
					}
					else if((args[2]+" "+args[3]+" "+args[4]).equals("Astar Manhattan Distance")==true)
					{
						BFS<Position> solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeManhattanDistance(s));
						controller.setSolution(solve.search(s).getSol(),args[1]);
						controller.printStr("solution for " +args[1]+ " is ready");
					}
					else
						controller.printStr("Algorithm is not exist");
				}
				else
					controller.printStr("Algorithm is not exist");
			}
		});
	}

	@Override
	public void exit() {
		threadpool.shutdown();
		try {
			while(!(threadpool.awaitTermination(10, TimeUnit.SECONDS)));
		} catch (InterruptedException e) {
			controller.printStr(e.getMessage());
		}
	}
}
