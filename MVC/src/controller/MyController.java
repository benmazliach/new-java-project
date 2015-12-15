package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

public class MyController implements Controller {

	Model<Position> model;
	View view;
	HashMap<String, Command> commandsMap;
	HashMap<String, Maze3d> maze3dMap;
	
	public MyController(HashMap<String, Command> commandsMap) {
		this.commandsMap = commandsMap;
		this.maze3dMap = new HashMap<String, Maze3d>();
		putCommandsMap();
	}
	
	public void putCommandsMap()
	{	/*
		//dir <path>
		commandsMap.put("dir", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				
			}
		});*/
		
		//generate 3d maze <name> <x,y,z,type of generate>
		commandsMap.put("generate maze 3d", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				int x = Integer.parseInt(args[4]);
				int y = Integer.parseInt(args[5]);
				int z = Integer.parseInt(args[6]);
				
				model.generateMaze3d(x, y, z, args[7], args[3]);
			}
		});
		
		//display
		commandsMap.put("display", new Command() {
					
			@Override
			public void doCommand(String[] args) {
					
			}
		});
		/*
		//display cross
		commandsMap.put("display cross section by {X,Y,Z}", new Command() {
				
			@Override
			public void doCommand(String[] args) {
						
			}
		});
		//save maze
		commandsMap.put("save maze", new Command() {
					
			@Override
			public void doCommand(String[] args) {
							
			}
		});
		//load maze
		commandsMap.put("load maze", new Command() {
						
			@Override
			public void doCommand(String[] args) {
							
			}
		});
		//maze size
		commandsMap.put("maze size", new Command() {
								
			@Override
			public void doCommand(String[] args) {
									
			}
		});
		//file size
		commandsMap.put("file size", new Command() {
										
			@Override
			public void doCommand(String[] args) {
											
			}
		});
		//solve
		commandsMap.put("solve", new Command() {
										
			@Override
			public void doCommand(String[] args) {
											
			}
		});
		//display solution
		commandsMap.put("display solution", new Command() {
										
			@Override
			public void doCommand(String[] args) {
											
			}
		});*/
		//exit
		commandsMap.put("exit", new Command() {
											
			@Override
			public void doCommand(String[] args) {
				System.out.println("exit");
			}
		});
	}
	
	@Override
	public void setModel(Model<Position> model) {
		this.model = model;
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void setMaze3d(Maze3d maze,String name) {
		if(maze3dMap.containsKey(name)==false)
		{
			maze3dMap.put(name, maze);
			String s = "maze "+name+" is ready";
			view.printString(s);
		}
	}

}
