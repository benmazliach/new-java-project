package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

/**
 * <h1>  View Interface <h1>
 * This interface manage the view for the client
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public interface View {

	/**
	 * start the view
	 */
	void start();
	/**
	 * print any string 
	 * @param String s
	 */
	void printString(String s);
	/**
	 * print maze3d
	 * @param int[][][],String - the maze,maze name
	 */
	void printMaze3d(int[][][] arr,String name);
	/**
	 * print cross by sectionType
	 * @param int[][],char,String,int - the cross maze,section type,maze name,section index
	 */
	void crossSectionPrint(int[][] arr,char sectionType,String name,int section);
	/**
	 * display the solution
	 * @param ArrayList<State<Position>>,String - the solution is arrayList, the solution come from this name
	 */
	void displaySolution(ArrayList<State<Position>> arrayList,String name);
	
}
