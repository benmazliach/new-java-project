package presenter;

import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import model.Model;
import view.View;

public class MyPresenter implements Presenter,Observer{

	Model model;
	View view;
	HashMap<String, Command> commandsMap;
	
	public MyPresenter(Model model,View view) {
		this.view = view;
		this.model = model;
		this.commandsMap = new HashMap<String, Command>();
		putCommandsMap();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o==view)
		{
			String[] args = view.getArgs();
			boolean dos = false;
			String s = null;
			for(int i=0;i<args.length;i++)
			{
				if(commandsMap.containsKey(s)== true && dos == false)
				{
					dos = true;
				}
				else
				{
					if(dos==false)
					{
						if(s==null)
							s = args[i];
						else if(s!=null)
							s = s + " " +args[i];
					}
				}
			}
			if(commandsMap.containsKey(s)==true)
			{
				commandsMap.get(s).doCommand(args);
			}
			else
			{
				view.printString("Error");
			}
		}
		if(o==model)
		{
			if(arg.getClass().getName().equals("java.lang.String"))
			{
				String str = (String) arg;
				int index = model.getIndex();
				switch (index) {
				case 0: view.printString(str);
						break;
				case 1: String[] args = str.split(" ");
						view.crossSectionPrint(model.getCross(), args[0], args[1], args[2]);
						break;
				}
			}
		}
	}
	
	public void putCommandsMap()
	{
		//dir <path>
		commandsMap.put("dir", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				File file = new File(args[1]);
				if(file.exists()==true)
				{
					view.printString("The path is : ");
					try {
						String[] strings = file.list();
						String str = null;
						for(int i=0;i<strings.length;i++) {
							if(str==null)
								str = strings[i] + "\n";
							else
								str += strings[i] + "\n";
						}
						view.printString(str);
					}
					catch (NullPointerException e){
						view.printString(e.getMessage());
					}
				}
				else
					view.printString("Path is not exist");
			}
		});
		
		//generate 3d maze <name> <x,y,z,type of generate>
		commandsMap.put("generate 3d maze", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				if(args.length==8)
				{
					int x = Integer.parseInt(args[4]);
					int y = Integer.parseInt(args[5]);
					int z = Integer.parseInt(args[6]);
				
					model.generateMaze3d(x, y, z, args[7], args[3]);
				}
				else if(args.length==3)
				{
					model.generateMaze3d();
				}
				else
					view.printString("Error");
			}
		});
		
		//display
		commandsMap.put("display", new Command() {
					
			@Override
			public void doCommand(String[] args) {
				//display cross section by {X,Y,Z} <index> for <name>
				if(args.length==8)
				{
					if((args[0]+" "+args[1]+" "+args[2]+" "+args[3]).equals("display cross section by"))
					{
						if(model.checkMazeHash(args[7]) == true)
						{
							int section = Integer.parseInt(args[5]);
							model.crossBySection(model.getMaze3d(args[7]),args[7],section,args[4].toLowerCase().charAt(0));
						}
						else
							view.printString("Maze " + args[7] + " is not exist!");
					}
					else
						view.printString("Error");
				}//display solution <name>
				else if(args.length==3)
				{
					if((args[0]+" "+args[1]).equals("display solution"))
					{
						if(model.checkMazeHash(args[2]) == true)
						{
							view.displaySolution(model.getSolution(args[2]),args[2]);
						}
						else
							view.printString("Solution for maze " + args[2] + " is not exist!");
					}
					else
						view.printString("Error");
				}//display <name>
				else if(model.checkMazeHash(args[1]) == true)
				{
					view.printMaze3d(model.getArrayMaze3d(args[1]), args[1]);
				}
				else
					view.printString("Maze " + args[1] + " is not exist!");
			}
		});
		
		//save maze <name> <file name>
		commandsMap.put("save maze", new Command() {
					
			@Override
			public void doCommand(String[] args) {
				if(model.checkMazeHash(args[2]) == true)
					model.saveMaze(model.getMaze3d(args[2]),args[2],args[3]);
				else
					view.printString("Maze " + args[2] + " is not exist!");			
			}
		});
		
		//load maze <file name> <name>
		commandsMap.put("load maze", new Command() {
						
			@Override
			public void doCommand(String[] args) {
				model.loadMaze(args[3], args[2]);
			}
		});
		//maze size <name>
		commandsMap.put("maze size", new Command() {
								
			@Override
			public void doCommand(String[] args) {
				if(model.checkMazeHash(args[2]) == true)
					model.mazeSize(model.getMaze3d(args[2]),args[2]);
				else
					view.printString("Maze " + args[2] + " is not exist!");	
			}
		});
		//file size <file name>
		commandsMap.put("file size", new Command() {
										
			@Override
			public void doCommand(String[] args) {
				model.fileSize(args);							
			}
		});
		//solve <name> <algorithm>
		commandsMap.put("solve", new Command() {
										
			@Override
			public void doCommand(String[] args) {
				if(model.checkMazeHash(args[1])==true)
					model.solveMaze(args,model.getMaze3d(args[1]));
				else
					view.printString("Maze " + args[2] + " is not exist!");	
			}
		});
		//exit
		commandsMap.put("exit", new Command() {
											
			@Override
			public void doCommand(String[] args) {
				model.exit();
			}
		});
	}
	
	@Override
	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	
	@Override
	public void printStr(String str) {
		view.printString(str);
	}
}


