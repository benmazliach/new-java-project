package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

public class MyController implements Controller {

	Model<Position> m;
	View v;
	HashMap<String, Command> commandsMap;
	HashMap<String, Maze3d> maze3dMap;
	
	public MyController(Model<Position> m,View v,HashMap<String, Command> commandsMap) {
		this.m = m;
		this.v = v;
		this.commandsMap = commandsMap;
		this.maze3dMap = new HashMap<String, Maze3d>();
		
	}
	
	public void putCommandsMap()
	{	
		//dir <path>
		commandsMap.put("dir", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				
			}
		});
		//generate 3d maze <name> <other params>
		commandsMap.put("generate 3d maze", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				
			}
		});
		//display
		commandsMap.put("display", new Command() {
					
			@Override
			public void doCommand(String[] args) {
					
			}
		});
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
		});
		//exit
		commandsMap.put("exit", new Command() {
											
			@Override
			public void doCommand(String[] args) {
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}
	
	@Override
	public void setModel(Model<Position> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setView(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaze3d(Maze3d maze,String name) {
		if(maze3dMap.containsKey(name)==false)
		{
			maze3dMap.put(name, maze);
			v.printString("maze "+name+" is ready");
		}
	}

}
