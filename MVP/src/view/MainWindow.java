package view;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import model.MyModel;
import presenter.MyPresenter;
import presenter.Properties;

public class MainWindow extends BasicWindow implements View{

	Timer timer;
	TimerTask task;
	private String[] args;
	private String[] mazes;
	private String nameCurrentMaze;
	private MazeDisplayer mazeDisplayer;
	private Maze3d maze;
	private Group arrowsGroup;
	private String solveAlg;
	
	public MainWindow(String title, int x, int y) {
		super(title, x, y);
	}
	

	@Override
	void initWidgets() {
		
		//TODO : save maze,load maze,dir,maze size,file size
		//TODO : תמיד לפתוח הודעה אם לא קיים מבוך או קובץ
		//TODO : Load file - שואל בעזרת דיאלוג איזה קובץ לטעון
		
		shell.setLayout(new GridLayout(2,false));
		
		//להוסיף TOOLBAR עם על מה שכתבתי לך ועם כל מה שכתוב במטלה של חלק 4
		///////////////////////////////////////toolbar//////////////////////////////////////////////////
		Menu menuBar, fileInMenuBar, gameInMenuBar, helpInMenuBar;
		MenuItem fileMenuHeader, gameMenuHeader, helpMenuHeader, 
		generateItem, solveItem, openPropertiesItem, exitItem, aboutItem;
		
		menuBar = new Menu(shell,SWT.BAR);
		
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
		
		fileInMenuBar = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileInMenuBar);
		
		openPropertiesItem = new MenuItem(fileInMenuBar, SWT.PUSH);
		openPropertiesItem.setText("&Open Properties");
		
		exitItem = new MenuItem(fileInMenuBar, SWT.PUSH);
		exitItem.setText("&Exit");
		
		gameMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		gameMenuHeader.setText("&Game");
		
		gameInMenuBar = new Menu(shell, SWT.DROP_DOWN);
		gameMenuHeader.setMenu(gameInMenuBar);
				
		generateItem = new MenuItem(gameInMenuBar, SWT.PUSH);
		generateItem.setText("&Generate maze");
			
		solveItem = new MenuItem(gameInMenuBar, SWT.PUSH);
		solveItem.setText("&Solve maze");
		
		helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuHeader.setText("&Help");
		
		helpInMenuBar = new Menu(shell, SWT.DROP_DOWN);
		helpMenuHeader.setMenu(helpInMenuBar);
		
		aboutItem = new MenuItem(helpInMenuBar, SWT.PUSH);
		aboutItem.setText("&About");
		
		shell.setMenuBar(menuBar);
		
		//////////////////////////////widgets//////////////////////////////////
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
		
		//display solution maze button
		Button solveButton=new Button(buttonsGroup, SWT.PUSH);
		solveButton.setText("Display solution");
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		
		//move character to goal button
		Button solButton=new Button(buttonsGroup, SWT.PUSH);
		solButton.setText("Solve maze");
		solButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
						
		//hint button
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
		
		/////////////////////////////////////listeners///////////////////////////////////
		
		exitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent event) {	
	    		shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		openPropertiesItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Open Properties");
		        
		        try {
					fd.setFilterPath(new java.io.File(".").getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        String[] filterExt = { "*.xml" };
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        
		        if(selected != null){
		        	XMLDecoder d;
		    		Properties properties = new Properties();
		    		try {
		    			d = new XMLDecoder(new BufferedInputStream(new FileInputStream(selected)));
		    			properties = (Properties) d.readObject();
		    			d.close();
		    		} catch (FileNotFoundException e) {
		    			e.printStackTrace();
		    		}
		    		setChanged();
		    		notifyObservers(properties);
		        }
		        	
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}				
		});
		
		aboutItem.addSelectionListener(new SelectionListener() {
			////////////////////////////////////////TODO \n???////////////////////////////////////////////////////
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox mb = new MessageBox(shell , SWT.ICON_INFORMATION | SWT.YES);
				String message = "Developers: Ben mazliach & Or moshe\n\n";
				message += "Verision: 1.0\n\n";
				message += "Contact us via e-mail:\n";
				message += "Ben - benmazliach@gmail.com, ";
				message += "Or - ormoshe2204@gmail.com";

				mb.setText("About");
				mb.setMessage(message);
				mb.open();
				}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		b[0].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null && mazeDisplayer.isFinish()==false)
				{
					mazeDisplayer.moveUp();
					possibleMoves(b);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		b[1].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null && mazeDisplayer.isFinish()==false)
				{
					movePageUp();
					possibleMoves(b);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		b[2].addSelectionListener(new SelectionListener() {
				
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null && mazeDisplayer.isFinish()==false)
				{
					mazeDisplayer.moveLeft();
					possibleMoves(b);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
			
		b[3].addSelectionListener(new SelectionListener() {
		
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null && mazeDisplayer.isFinish()==false)
				{
					mazeDisplayer.moveRight();					
					possibleMoves(b);
				}
			}
		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
			
		b[4].addSelectionListener(new SelectionListener() {
				
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null && mazeDisplayer.isFinish()==false)
				{
					mazeDisplayer.moveDown();
					possibleMoves(b);
				}
			}
				
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
				
		b[5].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null && mazeDisplayer.isFinish()==false)
				{
					movePageDown();
					possibleMoves(b);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
		});
				
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
						mazeDisplayer.setFinish(false);
						nameCurrentMaze = mazes[list.getFocusIndex()];
						setCommand(("display " + nameCurrentMaze).split(" "));
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
		
		solButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(nameCurrentMaze!=null)
				{
					maze.setStartPosition(mazeDisplayer.getCharacter());
					setCommand("solveAlgorithm".split(" "));
					setCommand(("solve "+nameCurrentMaze+" "+solveAlg).split(" "));
					maze.setStartPosition(mazeDisplayer.getStartPosition());
					setCommand(("display solution "+nameCurrentMaze).split(" "));
					timer=new Timer();
					task=new TimerTask() {
						@Override
						public void run() {
							display.syncExec(new Runnable() {
								@Override
								public void run() {
									Walk(mazeDisplayer.getSol());
								}
							});
						}
					};				
					timer.scheduleAtFixedRate(task, 0, 500);
				}
				else
				{
					MessageBox message = new MessageBox(shell,SWT.ICON_ERROR| SWT.YES);
					message.setMessage("You have to choose a maze first!!!");
					message.open();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		solveButton.addSelectionListener(new SelectionListener() {//לא עובד עד הסוף
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(nameCurrentMaze!=null)
				{
					maze.setStartPosition(mazeDisplayer.getCharacter());
					setCommand("solveAlgorithm".split(" "));
					setCommand(("solve "+nameCurrentMaze+" "+solveAlg).split(" "));
					maze.setStartPosition(mazeDisplayer.getStartPosition());
					setCommand(("display solution "+nameCurrentMaze).split(" "));
				}
				else
				{
					MessageBox message = new MessageBox(shell,SWT.ICON_ERROR| SWT.YES);
					message.setMessage("You have to choose a maze first!!!");
					message.open();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		hintButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeDisplayer.getMazeData()!=null)
				{
					maze.setStartPosition(mazeDisplayer.getCharacter());
					setCommand(("hint "+nameCurrentMaze).split(" "));
					maze.setStartPosition(mazeDisplayer.getStartPosition());
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeDisplayer.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(mazeDisplayer.isFinish()==false)
				{
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
				}
				possibleMoves(b);
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
		//System.out.println(isInt(s));
		//הצגת הודעה עם מספר הצעדים שצריך לעשות עד היעד
		//זה של הרמז
		if(isInt(s)==true)
		{
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION| SWT.YES);
			messageBox.setMessage("You need to do "+s+" steps to treasure");
			messageBox.open();
		}
		else
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
		mazeDisplayer.setSol(new Solution<Position>(temp));
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
		
		if(possibleMoves!=null && mazeDisplayer.isFinish()==false)
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


	public String getSolveAlg() {
		return solveAlg;
	}


	public void setSolveAlg(String solveAlg) {
		this.solveAlg = solveAlg;
	}

	private void Walk(Solution<Position> sol){
		System.out.println(sol.getSol().size());
		if(sol.getSol().size()>0)
		{
			int x = sol.getSol().get(0).getState().getpX();
			int y = sol.getSol().get(0).getState().getpY();
			int z = sol.getSol().get(0).getState().getpZ();
			
			if(mazeDisplayer.getSection().equals("y"))
			{
				if(mazeDisplayer.getCharacter().getpX()>x)
					mazeDisplayer.moveLeft();
				else if(mazeDisplayer.getCharacter().getpX()<x)
					mazeDisplayer.moveRight();
				else if(mazeDisplayer.getCharacter().getpZ()>z)
					mazeDisplayer.moveUp();
				else if(mazeDisplayer.getCharacter().getpZ()<z)
					mazeDisplayer.moveDown();
				else if(mazeDisplayer.getCharacter().getpY()>y)
					movePageDown();
				else if(mazeDisplayer.getCharacter().getpY()<y)
					movePageUp();
			}
			sol.getSol().remove(0);
		}
		else
			timer.cancel();
	}
	
}
