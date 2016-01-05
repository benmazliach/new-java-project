package view;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import model.MyModel;
import presenter.MyPresenter;

public class MainWindow extends BasicWindow implements View{

	private String[] args;
	private String[] mazes;
	private String nameCurrentMaze;
	private MazeDisplayer mazeDisplayer;
	private Maze3d maze;
	private Group arrowsGroup;
	
	public MainWindow(String title, int x, int y) {
		super(title, x, y);
	}
	

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		
		//Buttons group
		Group buttonsGroup = new Group(shell, SWT.NONE);
		buttonsGroup.setText("Options:");
		buttonsGroup.setLayout(new GridLayout(1, true));
		
		//generate maze button
		Button generateButton=new Button(buttonsGroup, SWT.PUSH);
		generateButton.setText("Generate maze3d");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
				
		//dispaly maze button
		Button displayMazeButton=new Button(buttonsGroup, SWT.PUSH);
		displayMazeButton.setText("Display maze");
		displayMazeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		
		//MazeDisplayer
		mazeDisplayer = new Maze2D(shell, SWT.BORDER);
		mazeDisplayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 4));
		
		//generate maze button
		Button solveButton=new Button(buttonsGroup, SWT.PUSH);
		solveButton.setText("Solve maze");
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
						
		//dispaly maze button
		Button hintButton=new Button(buttonsGroup, SWT.PUSH);
		hintButton.setText("Hint");
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		
		//Sections group
		Group sectionGroup = new Group(shell, SWT.SHADOW_OUT);
		sectionGroup.setText("Choose section:");
		sectionGroup.setLayout(new GridLayout(1, true));
		
		//Sections radio buttons
		Button sectionXButton=new Button(sectionGroup, SWT.RADIO | SWT.SELECTED);
		sectionXButton.setText("Section by X");
		sectionXButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		Button sectionYButton=new Button(sectionGroup, SWT.RADIO | SWT.SELECTED);
		sectionYButton.setText("Section by Y");
		sectionYButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		Button sectionZButton=new Button(sectionGroup, SWT.RADIO | SWT.SELECTED);
		sectionZButton.setText("Section by Z");
		sectionZButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		
		//arrows group
		arrowsGroup = new Group(shell, SWT.NONE);
		arrowsGroup.setText("Posible moves:");
		arrowsGroup.setLayout(new GridLayout(3, false));
		
		Button[] b = new Button[6];
		
		b[0] = new Button(arrowsGroup, SWT.NONE);
		b[0].setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 2, 1));
		b[1] = new Button(arrowsGroup, SWT.NONE);
		b[1].setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		b[2] = new Button(arrowsGroup, SWT.NONE);
		b[2].setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		b[3] = new Button(arrowsGroup, SWT.NONE);
		b[3].setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		b[4] = new Button(arrowsGroup, SWT.NONE);
		b[4].setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 2, 1));
		b[5] = new Button(arrowsGroup, SWT.NONE);
		b[5].setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		
		possibleMoves(b);
		
		sectionXButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(nameCurrentMaze!=null)
				{
					mazeDisplayer.setSection("x");
					setCommand(("display cross section by X "+mazeDisplayer.getCharacter().getpX()+" for "+nameCurrentMaze).split(" "));
					possibleMoves(b);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		sectionYButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(nameCurrentMaze!=null)
				{
					mazeDisplayer.setSection("y");
					setCommand(("display cross section by Y "+mazeDisplayer.getCharacter().getpY()+" for "+nameCurrentMaze).split(" "));
					possibleMoves(b);
					}
				}
			
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

		sectionZButton.addSelectionListener(new SelectionListener() {
	
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(nameCurrentMaze!=null)
				{
					mazeDisplayer.setSection("z");
					setCommand(("display cross section by Z "+mazeDisplayer.getCharacter().getpZ()+" for "+nameCurrentMaze).split(" "));
					possibleMoves(b);
				}
			}
	
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.setEnabled(false);
				Shell generateShell = new Shell();
				generateShell.setSize(350, 250);
				generateShell.setLayout(new GridLayout(2,false));
				generateShell.setText("Generate maze3d");
				

				new Label(generateShell, SWT.None).setText("Maze name: ");
				Text t1 = new Text(generateShell, SWT.BORDER);
				new Label(generateShell, SWT.None).setText("X size: ");
				Text t2 = new Text(generateShell, SWT.BORDER);
				new Label(generateShell, SWT.None).setText("Y size: ");
				Text t3 = new Text(generateShell, SWT.BORDER);
				new Label(generateShell, SWT.None).setText("Z size: ");
				Text t4 = new Text(generateShell, SWT.BORDER);
				new Label(generateShell, SWT.None).setText("Generation type: ");
				String[] items = "MyMaze3dGenerator SimpleMaze3dGenerator".split(" ");
				Combo combo1 = new Combo(generateShell, SWT.DROP_DOWN);
				combo1.setItems(items);

				
				Button createMazeButton = new Button(generateShell, SWT.PUSH);
				createMazeButton.setText("Create maze");
				createMazeButton.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
				
				createMazeButton.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if(t1.getText().equals("")==false && t2.getText().equals("")==false &&
								t3.getText().equals("")==false && t4.getText().equals("")==false && combo1.getText().equals("")==false)
						{
							String temp = "";
							if(isInt(t2.getText())==false)
								temp = "Wrong X parameter\n";
							if(isInt(t3.getText())==false)
								temp += "Wrong Y parameter\n";
							if(isInt(t4.getText())==false)
								temp += "Wrong Z parameter\n";
							if(temp.equals(""))
							{
								String s = "generate 3d maze "+t1.getText()+" "+t2.getText()+" "+t3.getText()+" "+t4.getText()+" "+combo1.getText();
								String[] args = s.split(" ");
								setCommand(args);
								MessageBox ready = new MessageBox(generateShell , SWT.ICON_INFORMATION | SWT.YES);
								ready.setMessage("Maze saved");
								ready.open();
								generateShell.close();
							}
							else
							{
								MessageBox wrong = new MessageBox(generateShell , SWT.ICON_ERROR | SWT.YES);
								wrong.setMessage(temp);
								wrong.open();
							}
								
						}
						else
						{
							MessageBox wrong = new MessageBox(generateShell , SWT.ICON_ERROR | SWT.YES);
							wrong.setMessage("Wrong parameters!!!");
							wrong.open();
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				
				//exit
				generateShell.addListener(SWT.Close, new Listener() {
				    	@Override
				      public void handleEvent(Event event) {
				    		shell.setEnabled(true);
				      }
				 });
				generateShell.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
				
		displayMazeButton.addSelectionListener(new SelectionListener() {
					
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.setEnabled(false);
				Shell chooseShell = new Shell();
				chooseShell.setSize(350, 250);
				chooseShell.setLayout(new GridLayout(1,true));
				chooseShell.setText("Choose maze3d");
				new Label(chooseShell, SWT.None).setText("Choose maze3d to be displayed:");
						
				setCommand("mazeName".split(" "));
				String[] mazes = getMazes();
						
						
				List list = new List(chooseShell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);    
				list.setItems(mazes);    
				list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
				
				Button displayMazeButton = new Button(chooseShell, SWT.PUSH);
				displayMazeButton.setText("Dispaly maze");
				displayMazeButton.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 1, 1));
				
				displayMazeButton.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						shell.setEnabled(true);
						mazeDisplayer.setSection("y");
						mazeDisplayer.setSol(null);
						nameCurrentMaze = mazes[list.getFocusIndex()];
						setCommand(("display "+nameCurrentMaze).split(" "));
						possibleMoves(b);
						chooseShell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				
				chooseShell.open();
				
				//exit
				chooseShell.addListener(SWT.Close, new Listener() {
			       	  @Override
				      public void handleEvent(Event event) {
				    		shell.setEnabled(true);
				      }
				 });
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		solveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				maze.setStartPosition(mazeDisplayer.getCharacter());
				setCommand(("solve "+nameCurrentMaze+" "+"BFS").split(" "));
				maze.setStartPosition(mazeDisplayer.getStartPosition());
				setCommand(("display solution "+nameCurrentMaze).split(" "));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeDisplayer.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				switch(arg0.keyCode)
				{
				case SWT.ARROW_UP: mazeDisplayer.moveUp();
						break;
				case SWT.ARROW_DOWN: mazeDisplayer.moveDown();
						break;
				case SWT.ARROW_LEFT: mazeDisplayer.moveLeft();
						break;
				case SWT.ARROW_RIGHT: mazeDisplayer.moveRight();
						break;
				case SWT.PAGE_UP: movePageUp();
					break;
				case SWT.PAGE_DOWN: movePageDown();
					break;
				}
				possibleMoves(b);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		
		//exit
		shell.addListener(SWT.Close, new Listener() {
	       	  @Override
		      public void handleEvent(Event event) {
		    		MessageBox messageBox = new MessageBox(shell,SWT.ICON_QUESTION| SWT.YES | SWT.NO);
		    		messageBox.setMessage("Do you really want to exit?");
		    		setCommand("exit".split(" "));
		    		event.doit = messageBox.open () == SWT.YES;
		      }
		 });
	}

	@Override
	public void start() {
		run();
	}
	
	public static void main(String[] args) {
		presenter.Properties pr = new presenter.Properties();
		pr.setAlgorithmGenerateName("MyMaze3dGenerator");
		pr.setAlgorithmSearchName("BFS");
		pr.setNumOfThreads(10);
		pr.setXSize(15);
		pr.setYSize(1);
		pr.setZSize(15);
		pr.setUserInterface("GUI");
		pr.setMazeName("mainMaze");
		MyModel m = new MyModel(pr);
		MainWindow v=new MainWindow("Menu", 1000, 700);
		MyPresenter p = new MyPresenter(m,v);
		
		m.addObserver(p);
		v.addObserver(p);
		
		v.start();
	}
	
	public void movePageUp()
	{
		if(mazeDisplayer.getSection().equals("y")==true)
		{
			int pY = mazeDisplayer.getCharacter().getpY()+1;
			if(maze.getYSize()>pY)
			{
				if(maze.returnValue(mazeDisplayer.getCharacter().getpX(), pY, mazeDisplayer.getCharacter().getpZ())==0)
				{
					mazeDisplayer.getCharacter().setpY(pY);
					setCommand(("display cross section by Y "+pY+" for "+nameCurrentMaze).split(" "));
				}
			}
		}
		else if(mazeDisplayer.getSection().equals("x")==true)
		{
			int pX = mazeDisplayer.getCharacter().getpX()+1;
			if(maze.getYSize()>pX)
			{
				if(maze.returnValue(pX, mazeDisplayer.getCharacter().getpY(), mazeDisplayer.getCharacter().getpZ())==0)
				{
					mazeDisplayer.getCharacter().setpX(pX);
					setCommand(("display cross section by X "+pX+" for "+nameCurrentMaze).split(" "));
				}
			}
		}
		else if(mazeDisplayer.getSection().equals("z")==true)
		{
			int pZ = mazeDisplayer.getCharacter().getpZ()+1;
			if(maze.getYSize()>pZ)
			{
				if(maze.returnValue(mazeDisplayer.getCharacter().getpX(), mazeDisplayer.getCharacter().getpY(), pZ)==0)
				{
					mazeDisplayer.getCharacter().setpZ(pZ);
					setCommand(("display cross section by Z "+pZ+" for "+nameCurrentMaze).split(" "));
				}
			}
		}
	}
	
	public void movePageDown()
	{
		if(mazeDisplayer.getSection().equals("y")==true)
		{
			int pY = mazeDisplayer.getCharacter().getpY()-1;
			if(pY>=0)
			{
				if(maze.returnValue(mazeDisplayer.getCharacter().getpX(), pY, mazeDisplayer.getCharacter().getpZ())==0)
				{
					mazeDisplayer.getCharacter().setpY(pY);
					setCommand(("display cross section by Y "+pY+" for "+nameCurrentMaze).split(" "));
				}
			}
		}
		else if(mazeDisplayer.getSection().equals("x")==true)
		{
			int pX = mazeDisplayer.getCharacter().getpX()-1;
			if(pX>=0)
			{
				if(maze.returnValue(pX, mazeDisplayer.getCharacter().getpY(), mazeDisplayer.getCharacter().getpZ())==0)
				{
					mazeDisplayer.getCharacter().setpX(pX);
					setCommand(("display cross section by X "+pX+" for "+nameCurrentMaze).split(" "));
				}
			}
		}
		else if(mazeDisplayer.getSection().equals("z")==true)
		{
			int pZ = mazeDisplayer.getCharacter().getpZ()-1;
			if(pZ>=0)
			{
				if(maze.returnValue(mazeDisplayer.getCharacter().getpX(), mazeDisplayer.getCharacter().getpY(), pZ)==0)
				{
					mazeDisplayer.getCharacter().setpZ(pZ);
					setCommand(("display cross section by Z "+pZ+" for "+nameCurrentMaze).split(" "));
				}
			}
		}
	}

	@Override
	public void displayString(String s) {
		System.out.println(s);
	}

	public Maze3d getMaze() {
		return maze;
	}


	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}


	@Override
	public void displayMaze3d(Maze3d maze, String name) {
		this.setMaze(maze);
		System.out.println(maze.getStartPosition());
		mazeDisplayer.setCharacter(maze.getStartPosition());
		mazeDisplayer.setStartPosition(maze.getStartPosition());
		mazeDisplayer.setGoalPosition(maze.getGoalPosition());
		mazeDisplayer.setMazeData(maze.getCrossSectionByY(mazeDisplayer.getStartPosition().getpY()));
		mazeDisplayer.redraw();
	}

	@Override
	public void displaySolution(Solution<Position> sol, String name) {
		ArrayList<State<Position>> temp = new ArrayList<State<Position>>();
		for(int i=sol.getSol().size()-1;i>=0;i--)
		{
			temp.add(sol.getSol().get(i));
		}
		mazeDisplayer.setSol(new Solution<>(temp));
		mazeDisplayer.redraw();
	}

	@Override
	public void setCommand(String[] args) {
		this.args = args;
		setChanged();
		notifyObservers();
	}

	@Override
	public void setArgs(String[] args) {
		this.args = args;
	}

	@Override
	public String[] getArgs() {
		return this.args;
	}

	@Override
	public void displayCrossSection(int[][] arr, String sectionType, String name, String section) {
		mazeDisplayer.setMazeData(arr);
		mazeDisplayer.redraw();
	}

	@Override
	public String[] getMazes() {
		return mazes;
	}

	@Override
	public void setMazes(String[] mazes) {
		this.mazes = mazes;
	}
	
	public void possibleMoves(Button[] b)
	{
		Image image = null;
		String[] possibleMoves = null;
		
		if(mazeDisplayer.getMazeData()!=null)
		{
			possibleMoves = mazeDisplayer.possibleMoves();
			String str = "";
			
			for (String string : possibleMoves) {
				str+= (string+" ");
			}
			
			//   Down / Up
			int h = 0;
			int up = 1;
			int down = 1;
			int maxSection = 0;
			if(mazeDisplayer.getSection().equals("y")==true)
			{
				h = mazeDisplayer.getCharacter().getpY();
				maxSection = maze.getYSize();
				if(h+1<maxSection)
					up = maze.returnValue(mazeDisplayer.getCharacter().getpX(), h+1, mazeDisplayer.getCharacter().getpZ());
				if(h-1>=0)
					down = maze.returnValue(mazeDisplayer.getCharacter().getpX(), h-1, mazeDisplayer.getCharacter().getpZ());
			}
			else if(mazeDisplayer.getSection().equals("x")==true)
			{
				h = mazeDisplayer.getCharacter().getpX();
				maxSection = maze.getXSize();
				if(h+1<maxSection)
					up = maze.returnValue(h+1,mazeDisplayer.getCharacter().getpY(),  mazeDisplayer.getCharacter().getpZ());
				if(h-1>=0)
					down = maze.returnValue(h-1, mazeDisplayer.getCharacter().getpY(), mazeDisplayer.getCharacter().getpZ());
			}
			else if(mazeDisplayer.getSection().equals("z")==true)
			{
				h = mazeDisplayer.getCharacter().getpZ();
				maxSection = maze.getZSize();
				if(h+1<maxSection)
					up = maze.returnValue(mazeDisplayer.getCharacter().getpX(), mazeDisplayer.getCharacter().getpY(), h+1);
				if(h-1>=0)
					down = maze.returnValue(mazeDisplayer.getCharacter().getpX(), mazeDisplayer.getCharacter().getpY(), h-1);
			}
			else
				return;
			
			if(up==0)
				str+="Up ";
			if(down==0)
				str+="Down ";
			
			possibleMoves = str.split(" ");
		}
		
		image = new Image(display, "resources/backward1.png");
		b[0].setImage(image);
		image= new Image(display, "resources/UP1.png");
		b[1].setImage(image);
		image= new Image(display, "resources/left1.png");
		b[2].setImage(image);
		image= new Image(display, "resources/right1.png");
		b[3].setImage(image);
		image = new Image(display, "resources/forward1.png");
		b[4].setImage(image);
		image= new Image(display, "resources/DOWN1.png");
		b[5].setImage(image);
		
		if(possibleMoves!=null)
		{
			for(int i=0;i<possibleMoves.length;i++)
			{
				if(possibleMoves[i].equals("Backward")==true)
				{
					image = new Image(display, "resources/backward2.png");
					b[0].setImage(image);
				}
				else if(possibleMoves[i].equals("Up")==true)
				{
					image= new Image(display, "resources/UP2.png");
					b[1].setImage(image);
				}
				else if(possibleMoves[i].equals("Left")==true)
				{
					image= new Image(display, "resources/left2.png");
					b[2].setImage(image);
				}
				else if(possibleMoves[i].equals("Right")==true)
				{
					image= new Image(display, "resources/right2.png");
					b[3].setImage(image);
				}
				else if(possibleMoves[i].equals("Forward")==true)
				{
					image = new Image(display, "resources/forward2.png");
					b[4].setImage(image);
				}
				else if(possibleMoves[i].equals("Down")==true)
				{
					image= new Image(display, "resources/DOWN2.png");
					b[5].setImage(image);
				}
			}
		}
		
	}
	
	public boolean isInt(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)<'0' || str.charAt(i)>'9')
				return false;
		}
		return true;
	}

}
