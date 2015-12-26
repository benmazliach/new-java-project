package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

/**
 * <h1>  MyView class <h1>
 * This class define methods for each view system
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public abstract class CommonView implements View {

	/**
	 * start the view
	 */
	public abstract void start();
	/**
	 * print any string 
	 * @param String s
	 */
	public abstract void printString(String s);
	/**
	 * print maze3d
	 * @param int[][][],String - the maze,maze name
	 */
	public abstract void printMaze3d(int[][][] arr, String name);
	/**
	 * print cross by sectionType
	 * @param int[][],char,String,int - the cross maze,section type,maze name,section index
	 */
	public abstract void crossSectionPrint(int[][] arr, char sectionType, String name, int section);
	/**
	 * display the solution
	 * @param ArrayList<State<Position>>,String - the solution is arrayList, the solution come from this name
	 */
	public abstract void displaySolution(ArrayList<State<Position>> arrayList, String name);

}
