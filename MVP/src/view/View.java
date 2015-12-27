package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public interface View {

	void start();
	void printString(String s);
	void printMaze3d(int[][][] arr,String name);
	void displaySolution(Solution<Position> sol,String name);
	public void setCommand(String command,String[] args);
	public void setArgs(String[] args);
	public String[] getArgs();
	public void setCommand(String command);
	public String getCommand();
	void crossSectionPrint(int[][] arr, String sectionType, String name, String section);
}
