package model;

import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import controller.Controller;

public class MyModel implements Model <Position>{

	Controller c;

	@Override
	public void generateMaze3d(int x, int y, int z, String generate,String name) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3dGenerator mg = null;
				//default simple
				if(generate.equals("MyMaze3dGenerator")==true)
					mg = new MyMaze3dGenerator(x,y,z);
				else
					mg = new MyMaze3dGenerator(x,y,z);
				c.setMaze3d(mg.getMaze(),name);
			}
		}).start();
	}

}
