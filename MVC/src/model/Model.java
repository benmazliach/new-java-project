package model;

public interface Model<Position> {
	
	void generateMaze3d(int x,int y,int z,String generate,String name);
	void display(String name);
	
}
