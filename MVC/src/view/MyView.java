package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import controller.Controller;

/**
 * <h1>  MyView class <h1>
 * This class implements View and manage the view for the client
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public class MyView extends CommonView {

	Controller c;
	CLI cli;
	
	/**
	 * Constructor - initialize view
	 * @param Controller,CLI
	 */
	public MyView(Controller c,CLI cli) {
		this.c = c;
		this.cli = cli;
	}
	
	/**
	 * start the view
	 */
	@Override
	public void start() {
		cli.start();
	}

	/**
	 * print any string 
	 * @param String s
	 */
	@Override
	public void printString(String s) {
		cli.getOut().println(s);
		cli.getOut().flush();
	}

	/**
	 * print maze3d
	 * @param int[][][],String - the maze,maze name
	 */
	@Override
	public void printMaze3d(int[][][] arr,String name) {
		cli.getOut().println("Maze name: "+name);
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				for(int k=0;k<arr[0][0].length;k++)
				{
					cli.getOut().print(arr[i][j][k] + " ");
				}
				cli.getOut().println();
			}
			cli.getOut().println();
		}
		cli.getOut().flush();
	}

	/**
	 * print cross by sectionType
	 * @param int[][],char,String,int - the cross maze,section type,maze name,section index
	 */
	@Override
	public void crossSectionPrint(int[][] arr, char sectionType, String name,int section) {
		cli.getOut().println("Maze name: "+name);
		cli.getOut().println("Section by "+sectionType+" = "+section);
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				cli.getOut().print(arr[i][j] + " ");
			}
			cli.getOut().println();
		}
		cli.getOut().flush();
	}

	/**
	 * display the solution
	 * @param ArrayList<State<Position>>,String - the solution is arrayList, the solution come from this name
	 */
	@Override
	public void displaySolution(ArrayList<State<Position>> arrayList,String name) {
		if(arrayList!=null)
		{
			printString("Solution of maze "+name+" is:");
			for (State<Position> state : arrayList) {
				cli.getOut().println(state.toString());
			}
			cli.getOut().flush();
		}
		else
			printString("Solution is not exist");
	}

}



