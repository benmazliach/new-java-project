package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
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
import model.MyModel;
import presenter.MyPresenter;

public class MenuWindow extends BasicWindow implements View{
	
	private String[] args;
	private String[] mazes;
	private String currentMaze;
	private MazeDisplayer mazeDisplayer;
	private int sectionY;
	private Position character;
	private Maze3d maze;
	
	
	public MenuWindow(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		

		Group group1 = new Group(shell, SWT.SHADOW_OUT);
		group1.setLayout(new GridLayout(1,false));
		group1.computeSize(100, 100);
		
		//generate maze button
		Button generateButton=new Button(group1, SWT.PUSH);
		generateButton.setText("Generate maze3d");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		
		//dispaly maze button
		Button displayMazeButton=new Button(group1, SWT.PUSH);
		displayMazeButton.setText("display maze");
		displayMazeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
	    
		//
		mazeDisplayer = new Maze3D(shell, SWT.BORDER);
		mazeDisplayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		Group sectionGroup = new Group(shell, SWT.SHADOW_OUT);
		sectionGroup.setLayout(new GridLayout(1, false));
		
		Button sectionX = new Button(sectionGroup, SWT.RADIO);
		sectionX.setText("Section by X:");
		
		
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
						currentMaze = mazes[list.getFocusIndex()];
						setCommand(("display "+currentMaze).split(" "));
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
				case SWT.PAGE_UP: 
					
					break;
				case SWT.PAGE_DOWN:
					
					break;
				}
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
		MenuWindow v=new MenuWindow("Menu", 700, 500);
		MyPresenter p = new MyPresenter(m,v);
		
		m.addObserver(p);
		v.addObserver(p);
		
		v.start();
	}

	@Override
	public void start() {
		run();
	}

	@Override
	public void displayString(String s) {
		System.out.println(s);
	}

	@Override
	public void displayMaze3d(Maze3d maze, String name) {
		sectionY = maze.getStartPosition().getpY();
		System.out.println("y: "+sectionY);
		System.out.println("Ymax: "+maze.getYSize());
		this.maze = maze;
		System.out.println(maze.getStartPosition());
		character = maze.getStartPosition();
		System.out.println(character);
		mazeDisplayer.setStartPosition(maze.getStartPosition());
		mazeDisplayer.setGoalPosition(maze.getGoalPosition());
		mazeDisplayer.setMazeData(maze.getCrossSectionByY(sectionY));
		mazeDisplayer.redraw();
	}

	@Override
	public void displaySolution(Solution<Position> sol, String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setCommand(String[] args) {
		this.args = args;
		this.setChanged();
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
		
		if(arr[character.getpX()][character.getpZ()]==0)
		{
			System.out.println(section);
			character.setpY(sectionY);
			mazeDisplayer.setMazeData(arr);
			mazeDisplayer.redraw();
		}
	}
	
	public boolean isInt(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)<'0' || str.charAt(i)>'9')
				return false;
		}
		return true;
	}
	
	public static int stringToInt(String str) {
		int z=10;
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)>='0' && str.charAt(i)<='9')
			{
				num=num*z+(str.charAt(i)-'0');
			}
		}
		return num;
	}

	public String[] getMazes() {
		return mazes;
	}

	public void setMazes(String[] mazes) {
		this.mazes = mazes;
	}

	
	public void posibleMoves(Group group,Maze3d maze)
	{

		Canvas canvas1 = new Canvas(group, SWT.FILL);
		canvas1.setLayoutData(new GridData(SWT.FILL ,SWT.FILL, true, true, 2, 3));
		canvas1.addPaintListener(new PaintListener() {
		      public void paintControl(PaintEvent e) {
		    	  
		        e.gc.drawImage(new Image(display, "resources/backward1.png"), 0, 0);
		        e.gc.drawImage(new Image(display, "resources/UP1.png"), 60, 10);
		      }
		    });
		    Canvas canvas2 = new Canvas(group, SWT.FILL);
			canvas2.setLayoutData(new GridData(SWT.FILL ,SWT.FILL, true, true, 2, 3));
		    canvas2.addPaintListener(new PaintListener() {
		      public void paintControl(PaintEvent e) {
		        e.gc.drawImage(new Image(display, "resources/left1.png"),0 , 0);
		        e.gc.drawImage(new Image(display, "resources/rigth1.png"), 60, 0);
		      }
		    });
		    Canvas canvas3 = new Canvas(group, SWT.FILL);
			canvas3.setLayoutData(new GridData(SWT.FILL ,SWT.FILL, true, true, 2, 3));
		    canvas3.addPaintListener(new PaintListener() {
		      public void paintControl(PaintEvent e) {
		    	e.gc.drawImage(new Image(display, "resources/forward1.png"),0, 0);
		    	e.gc.drawImage(new Image(display, "resources/DOWN1.png"), 60 , 0);
		      }
		    });
		    //////////////////////////////////////////////////////////
		    if(sectionY+1<maze.getYSize())
			{	
				System.out.println("c:\n");
				int[][] arr = maze.getCrossSectionByY(character.getpY()+1);
				for(int i=0;i<arr.length;i++)
				{
					for(int j=0;j<arr[0].length;j++)
					{
						System.out.print(arr[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("char: "+character);
				System.out.println(maze.getMaze()[character.getpY()+1][character.getpZ()][character.getpX()]);
				if(maze.getMaze()[character.getpY()+1][character.getpZ()][character.getpX()] == 0)
				{
					setCommand(("display cross section by Y "+(sectionY+1)+" for "+currentMaze).split(" "));
					//character.setpY(character.getpY()+1);
				}
				System.out.println("char: "+character);
			}
		    ///////////////////////////////////////////////////////
		    if(sectionY>0)
			{
				System.out.println("c:\n");
				int[][] arr = maze.getCrossSectionByY(character.getpY()-1);
				for(int i=0;i<arr.length;i++)
				{
					for(int j=0;j<arr[0].length;j++)
					{
						System.out.print(arr[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("char: "+character);
				System.out.println(maze.getMaze()[character.getpY()-1][character.getpZ()][character.getpX()]);
				if(maze.getMaze()[character.getpY()-1][character.getpZ()][character.getpX()] == 0)
				{
					setCommand(("display cross section by Y "+(sectionY-1)+" for "+currentMaze).split(" "));
					//character.setpY(character.getpY()-1);
				}
				System.out.println("char: "+character);
			}
	}
}

