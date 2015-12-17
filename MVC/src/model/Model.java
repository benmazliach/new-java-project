package model;

import algorithms.mazeGenerators.Maze3d;

/**
 * <h1>  Model Interface <h1>
 * This interface set the functional of the model side
 * This interface is doing all calculations touch data
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public interface Model {
	
	void generateMaze3d(int x,int y,int z,String generate,String name);
	void crossBySection(Maze3d maze,String name,int section,char typeSection);
	void saveMaze(Maze3d maze, String name, String fileName);
	void loadMaze(String name ,String fileName);
	void mazeSize(Maze3d maze, String name);
	void fileSize(String[] args);
	void solveMaze(String[] args, Maze3d maze);
	void exit();
	
}
