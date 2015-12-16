package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import controller.Controller;

public class MyModel implements Model <Position>{

	Controller controller;
	
	public MyModel(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void generateMaze3d(int x, int y, int z, String generate,String name) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3dGenerator mg = null;
				if(generate.equals("MyMaze3dGenerator")==true)
					mg = new MyMaze3dGenerator(x,y,z);
				else
					mg = new MyMaze3dGenerator(x,y,z);
				
				controller.setMaze3d(mg.getMaze(),name);
			}
		},"model generate").start();
	}
	

	@Override
	public void crossBySection(Maze3d maze, String name, int section, char typeSection) {
		if(typeSection=='x')
			controller.crossSection(maze.getCrossSectionByX(section), typeSection, name , section);
		else if(typeSection=='y')
			controller.crossSection(maze.getCrossSectionByY(section), typeSection, name , section);
		else if(typeSection=='z')
			controller.crossSection(maze.getCrossSectionByZ(section), typeSection, name , section);
	}

	
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	

}
