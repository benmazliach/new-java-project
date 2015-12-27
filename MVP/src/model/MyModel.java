package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Solution;
import algorithms.search.State;
import comperators.StateCostComparator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Position;


public class MyModel extends Observable implements Model
{
	private HashMap<String, Maze3d> mazeInFile;
	private ExecutorService threadpool;
	private HashMap<String, Maze3d> maze3dMap;
	private HashMap<String,Solution<Position>> solutionMap;
	private HashMap<Maze3d, Solution<Position>> mazeSolMap;
	private int[][] cross;
	private int index;

	public MyModel() {
		this.mazeInFile = new HashMap<String, Maze3d>();
		this.threadpool = Executors.newFixedThreadPool(10);  
		this.maze3dMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, Solution<Position>>();
		this.mazeSolMap = new HashMap<Maze3d, Solution<Position>>();
		cross = null;
	}
	
	@Override
	public void generateMaze3d(int x, int y, int z, String generate,String name) {
		threadpool.submit(new Callable<Maze3d>() {
			@Override
			public Maze3d call() {
				Maze3dGenerator mg = null;
				if(generate.equals("MyMaze3dGenerator")==true)
					mg = new MyMaze3dGenerator(x,y,z);
				else
					mg = new SimpleMaze3dGenerator(x,y,z);
				setMaze3d(mg.getMaze(), name);
				return mg.getMaze();
			}
		});
	}
	
	public int[][][] getArrayMaze3d(String name)
	{
		return maze3dMap.get(name).getMaze();
	}
	
	public Maze3d getMaze3d(String name)
	{
		return maze3dMap.get(name);
	}
	
	public boolean checkMazeHash(String name){
		return maze3dMap.containsKey(name);
	}
	public void setMaze3d(Maze3d maze,String name) {
		if(maze3dMap.containsKey(name)==false)
		{
			maze3dMap.put(name, maze);
			notifyString("Maze "+name+" is ready");
		}
		else
		{
			maze3dMap.replace(name, maze);
			notifyString("Maze "+name+" is ready");
		}
	}
	
	@Override
	public void crossBySection(Maze3d maze, String name, int section, char typeSection) {
		String s = null;
		if(typeSection=='x'){
			setCross(maze.getCrossSectionByX(section));
			s = typeSection + " " + name + " " +section;
			index = 1;
			this.setChanged();
			notifyObservers(s);
		}
		else if(typeSection=='y'){
			cross = maze.getCrossSectionByY(section);
			s = typeSection + " " + name + " " +section;
			index = 1;
			this.setChanged();
			notifyObservers(s);
		}
		else if(typeSection=='z'){
			setCross(maze.getCrossSectionByZ(section));
			s = typeSection + " " + name + " " +section;
			index = 1;
			this.setChanged();
			notifyObservers(s);
		}
	}
	@Override
	public void saveMaze(Maze3d maze, String name, String fileName) {
		MyCompressorOutputStream outFile;
		try {
			outFile = new MyCompressorOutputStream(new FileOutputStream(fileName));
			outFile.write(maze.toByteArray());
			mazeInFile.put(fileName, maze);
			outFile.close();
			notifyString("file "+fileName+" is ready");
		} catch (FileNotFoundException e) {
			notifyString(e.getMessage());
		} catch (IOException e) {
			notifyString(e.getMessage());
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
					if(maze3dMap.containsKey(name)==false)
					{
						maze3dMap.put(name, maze);
						notifyString("load maze "+name+" succeeded");
					}
					else
					{
						maze3dMap.replace(name, maze);
						notifyString("load maze "+name+" succeeded");
					}
				}
				else
				{
					index = -1;
					this.setChanged();
					notifyObservers("Can't load maze");
				}
			}
			else
			{
				notifyString("File " + fileName + " is not exist!");
			}
		} catch (FileNotFoundException e) {
			notifyString(e.getMessage());
		}
	}
	
	@Override
	public void mazeSize(Maze3d maze, String name) {
		notifyString("maze size " + name + " is " +(maze.getXSize()*maze.getYSize()*maze.getZSize()*Integer.SIZE)/8+ " bytes");
	}

	
	@Override
	public void fileSize(String[] args) {
		if(mazeInFile.containsKey(args[2])==true)
		{
			File f = new File(args[2]);
			notifyString("The size of the maze in the file is " +f.length()+ " bytes");
		}
		else
		{
			notifyString("File " + args[2] + " is not exist!");
		}
	}
	
	@Override
	public void solveMaze(String[] args, Maze3d maze) {
		if(mazeSolMap.containsKey(maze)==true)
			notifyString("Solution for maze "+args[1]+" is alredy exists");
		else
		{
		threadpool.submit(new Callable<Solution<Position>>() {
			@Override
			public Solution<Position> call() {
				Searchable<Position> s = new Maze3dDomain(maze);
				BFS<Position> solve = null;
				Solution<Position> sol = null;
				if(args.length>=3 && args.length<5)
				{
					if(args[2].equals("BFS")==true)
					{
						solve = new BFS<Position>(new StateCostComparator<Position>());
						sol = new Solution<Position>(solve.search(s).getSol());
						setMazeSol(sol, maze);
						setSolution(sol,args[1]);
					}
					else
						notifyString("Algorithm is not exist");
				}
				else if(args.length>=5)
				{
					if((args[2]+" "+args[3]+" "+args[4]).equals("Astar Air Distance")==true)
					{
						solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeAirDistance(s));
						sol = new Solution<Position>(solve.search(s).getSol());
						setMazeSol(sol, maze);
						setSolution(sol,args[1]);
					}
					else if((args[2]+" "+args[3]+" "+args[4]).equals("Astar Manhattan Distance")==true)
					{
						solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeManhattanDistance(s));
						sol = new Solution<Position>(solve.search(s).getSol());
						setMazeSol(sol, maze);
						setSolution(sol,args[1]);
					}
					else
						notifyString("Algorithm is not exist");
				}
				else
					notifyString("Algorithm is not exist");
				return sol;
			}
		});
		}
	}

	public void setSolution(Solution<Position> solution, String name) {
		if(solutionMap.containsKey(name)==false)
		{
			solutionMap.put(name, solution);
			notifyString("solution for " +name+ " is ready");
		}
	}
	
	public void setMazeSol(Solution<Position> solution, Maze3d maze) {
		if(mazeSolMap.containsKey(maze)==false)
		{
			mazeSolMap.put(maze, solution);
		}
	}
	
	@Override
	public void exit() {
		int i = 0;
		Collection<Maze3d> arr =  maze3dMap.values();
		MyCompressorOutputStream outFile;
		for (Maze3d maze3d : arr) {
			try {
				outFile = new MyCompressorOutputStream(new FileOutputStream(""+(i++)+".zip"));
				outFile.write(maze3d.toByteArray());
				outFile.close();
				GZIPOutputStream zip = new GZIPOutputStream(outFile);
				zip.write(maze3d.toByteArray());
				zip.flush();
				zip.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		threadpool.shutdown();
		try {
			while(!(threadpool.awaitTermination(10, TimeUnit.SECONDS)));
		} catch (InterruptedException e) {
			this.setChanged();
			notifyString(e.getMessage());
		}
		notifyString("Exit");
	}

	public int[][] getCross() {
		return cross;
	}

	public void setCross(int[][] cross) {
		this.cross = cross;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void notifyString(String str) {
		index = 0;
		this.setChanged();
		notifyObservers(str);
	}

	@Override
	public Solution<Position> getSolution(String name) {
		return solutionMap.get(name);
	}
	
	@Override
	public boolean checkSolutionHash(String name)
	{
		return solutionMap.containsKey(name);
	}
}



