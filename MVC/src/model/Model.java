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
	
	/**
	 * Creates the maze
	 * @param int x,int y,int z(size),String type of generation,String maze name
	 */
	void generateMaze3d(int x,int y,int z,String generate,String name);
	/**
	 * Creates two-dimensional array that contain the section
	 * @param Maze3d maze.String maze name,int number section,char type section(X,Y,Z)
	 */
	void crossBySection(Maze3d maze,String name,int section,char typeSection);
	/**
	 * Save maze in file
	 * @param Maze3d maze name, String maze name, String file Name
	 */
	void saveMaze(Maze3d maze, String name, String fileName);
	/**
	 * Load maze from file
	 * @param String maze name, String file Name
	 */
	void loadMaze(String name ,String fileName);
	/**
	 * Calculate size maze in the memory
	 * @param Maze3d maze name, String maze name
	 */
	void mazeSize(Maze3d maze, String name);
	/**
	 * Calculate size maze in the file
	 * @param String[] args
	 */
	void fileSize(String[] args);
	/**
	 * Creates solution to the maze
	 * @param String[] args, Maze3d maze
	 */
	void solveMaze(String[] args, Maze3d maze);
	/**
	 * Close all program
	 */
	void exit();
	
}
