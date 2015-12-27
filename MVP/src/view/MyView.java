package view;

import java.util.ArrayList;
import java.util.Observable;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class MyView extends Observable implements View {

	private CLI cli;
	private String command;
	private String[] args;
	
	public void setCommand(String command,String[] args)
	{
		this.command = command;
		this.args = args;
		this.setChanged();
		notifyObservers();
	}
	
	public MyView(CLI cli) {
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
	public void crossSectionPrint(int[][] arr, String sectionType, String name,String section) {
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
	public void displaySolution(Solution<Position> sol,String name) {
		ArrayList<State<Position>> print = sol.getSol();
		if(sol!=null)
		{
			printString("Solution of maze "+name+" is:");
			for (State<Position> state : print) {
				cli.getOut().println(state.toString());
			}
			cli.getOut().flush();
		}
		else
			printString("Solution is not exist");
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}



