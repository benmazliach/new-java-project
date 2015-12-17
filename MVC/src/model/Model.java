package model;

import algorithms.mazeGenerators.Maze3d;

public interface Model<Position> {
	
	void generateMaze3d(int x,int y,int z,String generate,String name);
	void crossBySection(Maze3d maze,String name,int section,char typeSection);
	void saveMaze(Maze3d maze, String name, String fileName);
	void loadMaze(String name ,String fileName);
	void mazeSize(Maze3d maze, String name);
	void fileSize(String[] args);
	void solveMaze(String[] args, Maze3d maze);
	void exit();
	
}
