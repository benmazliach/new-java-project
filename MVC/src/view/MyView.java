package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import controller.Controller;

public class MyView implements View {

	Controller c;
	CLI cli;
	
	public MyView(Controller c,CLI cli) {
		this.c = c;
		this.cli = cli;
	}
	
	@Override
	public void start() {
		cli.start();
	}

	@Override
	public void printString(String s) {
		cli.getOut().println(s);
		cli.getOut().flush();
	}

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

	@Override
	public void displaySolution(ArrayList<State<Position>> arrayList,String name) {
		printString("Solution of maze "+name+" is:");
		for (State<Position> state : arrayList) {
			cli.getOut().println(state.toString());
		}
		cli.getOut().flush();
	}

}



