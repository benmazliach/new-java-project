package model;

import algorithms.mazeGenerators.Maze3d;

public interface Model<Position> {
	
	void generateMaze3d(int x,int y,int z,String generate,String name);
	void crossBySection(Maze3d maze,String name,int section,char typeSection);
	
}
