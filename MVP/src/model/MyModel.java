package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
import algorithms.search.Solution;
import comperators.StateCostComparator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.Properties;


public class MyModel extends Observable implements Model
{
	private HashMap<String, Maze3d> mazeInFile;
	private HashMap<String, Maze3d> maze3dMap;
	private HashMap<String,Solution<Position>> solutionMap;
	private HashMap<Maze3d, Solution<Position>> mazeSolMap;
	private ExecutorService threadpool;
	private int[][] cross;
	private int index;
	private Properties properties;

	public MyModel(Properties properties) {
		this.mazeInFile = new HashMap<String, Maze3d>();
		this.maze3dMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, Solution<Position>>();
		this.mazeSolMap = new HashMap<Maze3d, Solution<Position>>();
		this.properties = properties;
		cross = null;
		loadMaze3dMapZip();
		this.threadpool = Executors.newFixedThreadPool(properties.getNumOfThreads());  
		generateMaze3d();
		solveMaze();
	}
	
	@Override
	public void generateMaze3d(int x, int y, int z, String generate,String name) {
		if(maze3dMap.containsKey(name)==true)
			notifyString("Maze "+name+" is alredy exists");
		else
		{
			Callable<Maze3d> call =  new Callable<Maze3d>() {
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
			};
			threadpool.submit(call);
		}
	}
	
	@Override
	public void generateMaze3d() {
		Callable<Maze3d> call =  new Callable<Maze3d>() {
			@Override
			public Maze3d call() {
				Maze3dGenerator mg = null;
				if(properties.getAlgorithmGenerateName().equals("MyMaze3dGenerator")==true)
					mg = new MyMaze3dGenerator(properties.getXSize(),properties.getYSize(),properties.getZSize());
				else
					mg = new SimpleMaze3dGenerator(properties.getXSize(),properties.getYSize(),properties.getZSize());
				setMaze3d(mg.getMaze(),properties.getMazeName());
				return mg.getMaze();
			}
		};
		threadpool.submit(call);
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
			if(section<maze.getXSize() && section>=0)
			{
				setCross(maze.getCrossSectionByX(section));
				s = typeSection + " " + name + " " +section;
				index = 1;
				this.setChanged();
				notifyObservers(s);
			}
			else
				notifyString("error");
		}
		else if(typeSection=='y'){
			if(section<maze.getYSize() && section>=0)
			{
				cross = maze.getCrossSectionByY(section);
				s = typeSection + " " + name + " " +section;
				index = 1;
				this.setChanged();
				notifyObservers(s);
			}
			else
				notifyString("error");
		}
		else if(typeSection=='z'){
			if(section<maze.getZSize() && section>=0)
			{
				setCross(maze.getCrossSectionByZ(section));
				s = typeSection + " " + name + " " +section;
				index = 1;
				this.setChanged();
				notifyObservers(s);
			}
			else
				notifyString("error");
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
		Callable<Solution<Position>> call = new Callable<Solution<Position>>() {
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
		};
		threadpool.submit(call);
		}
	}

	@Override
	public void solveMaze() {
		Callable<Solution<Position>> call = new Callable<Solution<Position>>() {
			@Override
			public Solution<Position> call() {
				Searchable<Position> s = new Maze3dDomain(maze3dMap.get(properties.getMazeName()));
				BFS<Position> solve = null;
				Solution<Position> sol = null;
				if(properties.getAlgorithmSearchName().equals("BFS")==true)
				{
					solve = new BFS<Position>(new StateCostComparator<Position>());
					sol = new Solution<Position>(solve.search(s).getSol());
					setMazeSol(sol, maze3dMap.get(properties.getMazeName()));
					setSolution(sol,properties.getMazeName());
				}
				else if(properties.getAlgorithmSearchName().equals("Astar Air Distance")==true)
				{
						solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeAirDistance(s));
						sol = new Solution<Position>(solve.search(s).getSol());
						setMazeSol(sol, maze3dMap.get(properties.getMazeName()));
						setSolution(sol,properties.getMazeName());
				}
				else if(properties.getAlgorithmSearchName().equals("Astar Manhattan Distance")==true)
				{
					solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeManhattanDistance(s));
					sol = new Solution<Position>(solve.search(s).getSol());
					setMazeSol(sol, maze3dMap.get(properties.getMazeName()));
					setSolution(sol, properties.getMazeName());
				}
				else
					notifyString("Algorithm is not exist");
			return sol;
			}
		};
		threadpool.submit(call);
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
		//Zip save
		saveMaze3dMapZip();
		threadpool.shutdown();
		try {
			while(!(threadpool.awaitTermination(10, TimeUnit.SECONDS)));
		} catch (InterruptedException e) {
			this.setChanged();
			notifyString(e.getMessage());
		}
		notifyString("Exit");
	}

	@Override
	public void saveMaze3dMapZip()
	{
		ObjectOutputStream obj = null;
		try {
			obj = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("mazeMap.zip")));
			obj.writeObject(maze3dMap);
			obj.flush();
		} catch (FileNotFoundException e) {
			notifyString(e.getMessage());
		} catch (IOException e) {
			notifyString(e.getMessage());
		} finally {
			try {
				obj.close();
			} catch (IOException e) {
				notifyString(e.getMessage());
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void loadMaze3dMapZip()
	{
		ObjectInputStream obj = null;
		try {
			obj = new ObjectInputStream(new GZIPInputStream(new FileInputStream("mazeMap.zip")));
			maze3dMap = (HashMap<String, Maze3d>) obj.readObject();
		} catch (FileNotFoundException e) {
			notifyString(e.getMessage());
		} catch (IOException e) {
			notifyString(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(obj!=null)
					obj.close();
			} catch (IOException e) {
				notifyString(e.getMessage());
			}
		}
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

	public Properties getProperties() {
		return properties;
	}
	
	public String[] getNamesMaze3d()
	{
		HashMap<String, Maze3d> temp = this.maze3dMap;
		String s=temp.toString();
		String[] t = s.split(", ");
		String[] k=null;
		for (int i = 0; i < t.length; i++) {
			if(i==0)
			{
				k = t[0].split("=");
				s = "";
				for (int j = 1; j < k[0].length(); j++) {
					s+=k[0].charAt(j);
				}
				t[0] = s;
			}
			else
			{
				
				k = t[i].split("=");
				t[i] = k[0];
			}
		}
		return t;
	}
}



