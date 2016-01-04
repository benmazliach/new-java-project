package view;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
/**
 * <h1>  MyView class <h1>
 * This class define methods for each view system
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public abstract class CommonView extends Observable implements View {

	String[] args;
	String[] mazes;
	
	/**
	 * start the view
	 */
	public abstract void start();
	/**
	 * display any string 
	 * @param String s
	 */
	public abstract void displayString(String s);
	/**
	 * display maze3d
	 * @param int[][][],String - the maze,maze name
	 */
	public abstract void displayMaze3d(Maze3d maze, String name);
	/**
	 * print cross by sectionType
	 * @param int[][],char,String,int - the cross maze,section type,maze name,section index
	 */
	public void setCommand(String[] args)
	{
		this.args = args;
		this.setChanged();
		notifyObservers();
	}

}
