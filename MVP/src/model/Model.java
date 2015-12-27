package model;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public interface Model {
	
	void generateMaze3d(int x,int y,int z,String generate,String name);
	void crossBySection(Maze3d maze,String name,int section,char typeSection);
	void saveMaze(Maze3d maze, String name, String fileName);
	void loadMaze(String name ,String fileName);
	void mazeSize(Maze3d maze, String name);
	void fileSize(String[] args);
	void solveMaze(String[] args, Maze3d maze);
	void exit();
	public int[][] getCross();
	public void setCross(int[][] cross);
	public int getIndex();
	public void setIndex(int index);
	public void setMaze3d(Maze3d maze,String name);
	public Maze3d getMaze3d(String name);
	public int[][][] getArrayMaze3d(String name);
	public boolean checkMazeHash(String name);
	public void notifyString(String str);
	public Solution<Position> getSolution(String name);
	public boolean checkSolutionHash(String name);
}
