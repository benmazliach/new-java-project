package view;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface View {

	void start();
	void printString(String s);
	void printMaze3d(int[][][] arr,String name);
	void displaySolution(Solution<Position> sol,String name);
	public void setCommand(String[] args);
	public void setArgs(String[] args);
	public String[] getArgs();
	void crossSectionPrint(int[][] arr, String sectionType, String name, String section);
}
