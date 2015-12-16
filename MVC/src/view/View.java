package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

public interface View {

	void start();
	void printString(String s);
	void printMaze3d(int[][][] arr,String name);
	void crossSectionPrint(int[][] arr,char sectionType,String name,int section);
	void displaySolution(ArrayList<State<Position>> arrayList,String name);
	
}
