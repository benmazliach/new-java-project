package controller;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import model.Model;
import view.View;

public interface Controller {

	void setModel(Model<Position> m);
	void setView(View v);
	void setMaze3d(Maze3d maze,String name);
	void crossSection(int[][] arr,char sectionType,String name,int section);
	void printStr(String str);
	void loadMaze(Maze3d maze, String name);
	void setSolution(ArrayList<State<Position>> solution, String name);
	
}
