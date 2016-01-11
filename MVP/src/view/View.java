package view;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface View {

	void start();
	void displayString(String s);
	void displayMaze3d(Maze3d maze,String name);
	void displaySolution(Solution<Position> sol,String name);
	public void setCommand(String[] args);
	public void setArgs(String[] args);
	public String[] getArgs();
	void displayCrossSection(int[][] arr, String sectionType, String name, String section);
	public String[] getMazes();
	public void setMazes(String[] mazes);
	public String getSolveAlg();
	public void setSolveAlg(String solveAlg);
}
