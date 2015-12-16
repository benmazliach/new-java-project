package controller;

import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import model.Model;
import view.View;

public class MyController implements Controller {

	Model<Position> model;
	View view;
	HashMap<String, Command> commandsMap;
	HashMap<String, Maze3d> maze3dMap;
	HashMap<String, ArrayList<State<Position>>> solutionMap;
	
	public MyController(HashMap<String, Command> commandsMap) {
		this.commandsMap = commandsMap;
		this.maze3dMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, ArrayList<State<Position>>>();
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
				if(args.length==8)
				{
					if((args[0]+" "+args[1]+" "+args[2]+" "+args[3]).equals("display cross section by"))
					{
						if(maze3dMap.containsKey(args[7]) == true)
						{
							int section = Integer.parseInt(args[5]);
							model.crossBySection(maze3dMap.get(args[7]),args[7],section,args[4].toLowerCase().charAt(0));
						}
					}
				}
				else if(args.length==3)
				{
					if((args[0]+" "+args[1]).equals("display solution"))
					{
						if(maze3dMap.containsKey(args[2]) == true)
						{
							view.displaySolution(solutionMap.get(args[2]),args[2]);
						}
					}
				}
				else if((args[0]).equals("display"))
				{
					if(maze3dMap.containsKey(args[1]) == true)
					{
						view.printMaze3d(maze3dMap.get(args[1]).getMaze(), args[1]);
					}
				}
				else
					view.printString("Maze " + args[1] + "is not exist!");
			}
		});
		//save maze
		commandsMap.put("save maze", new Command() {
					
			@Override
			public void doCommand(String[] args) {
				if(maze3dMap.containsKey(args[2]) == true)
					model.saveMaze(maze3dMap.get(args[2]),args[2],args[3]);
				else
					view.printString("Maze " + args[2] + " is not exist!");			
			}
		});
		//load maze
		commandsMap.put("load maze", new Command() {
						
			@Override
			public void doCommand(String[] args) {
				if(maze3dMap.containsKey(args[3]) == true)
					model.loadMaze(maze3dMap.get(args[3]),args[3], args[2]);
				else
					view.printString("Maze " + args[2] + " is not exist!");		
			}
		});
		//maze size
		commandsMap.put("maze size", new Command() {
								
			@Override
			public void doCommand(String[] args) {
				if(maze3dMap.containsKey(args[2]) == true)
					model.mazeSize(maze3dMap.get(args[2]),args[2]);
				else
					view.printString("Maze " + args[2] + " is not exist!");	
			}
		});
		//file size
		commandsMap.put("file size", new Command() {
										
			@Override
			public void doCommand(String[] args) {
				model.fileSize(args);							
			}
		});
		//solve
		commandsMap.put("solve", new Command() {
										
			@Override
			public void doCommand(String[] args) {
				if(maze3dMap.containsKey(args[1])==true)
					model.solveMaze(args,maze3dMap.get(args[1]));
				else
					view.printString("Maze " + args[2] + " is not exist!");	
			}
		});
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

	@Override
	public void crossSection(int[][] arr, char sectionType, String name , int section) {
		view.crossSectionPrint(arr, sectionType, name , section);
	}

	@Override
	public void printStr(String str) {
		view.printString(str);
	}

	@Override
	public void loadMaze(Maze3d maze, String name) {
		if(maze3dMap.containsKey(name)==false)
		{
			maze3dMap.put(name, maze);
			view.printString("load maze "+name+" succeeded");
		}
		else
		{
			maze3dMap.replace(name, maze);
			view.printString("load maze "+name+" succeeded");
		}
	}

	@Override
	public void setSolution(ArrayList<State<Position>> solution, String name) {
		solutionMap.put(name, solution);
	}

}



