package view;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.MyModel;
import presenter.MyPresenter;

public class MenuWindow extends BasicWindow implements View{
	
	private String[] args;
	
	public MenuWindow(String title, int width, int height) {
		super(title, width, height);
	}

	/*private void randomWalk(MazeDisplayer maze){
		Random r=new Random();
		boolean b1,b2;
		b1=r.nextBoolean();
		b2=r.nextBoolean();
		if(b1&&b2)
			maze.moveUp();
		if(b1&&!b2)
			maze.moveDown();
		if(!b1&&b2)
			maze.moveRight();
		if(!b1&&!b2)
			maze.moveLeft();
		
		maze.redraw();
	}*/
	
	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(1,false));
		
		//new Label(shell, SWT.LEFT).setText("Menu");
		
		//generate maze button
		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("Generate maze3d");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
				
		//dispaly maze button
		Button displayMazeButton=new Button(shell, SWT.PUSH);
		displayMazeButton.setText("Display maze");
		displayMazeButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		//exit
		shell.addListener(SWT.Close, new Listener() {
		    	@Override
		      public void handleEvent(Event event) {
		    		MessageBox messageBox = new MessageBox(shell,SWT.ICON_QUESTION| SWT.YES | SWT.NO);
		    		messageBox.setMessage("Do you really want to exit?");
		    		event.doit = messageBox.open () == SWT.YES;
		      }
		 });
		
		/*generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				{
					@Override
					public void run() {
						display.syncExec(new Runnable() {
							@Override
							public void run() {
								randomWalk(maze);
							}
						});
					}
				};				
				timer.scheduleAtFixedRate(task, 0, 100);				
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});*/
		
		
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
				    		MessageBox messageBox = new MessageBox(generateShell,SWT.ICON_QUESTION| SWT.YES | SWT.NO);
				    		messageBox.setMessage("Do you really want to exit?");
				    		if(event.doit = messageBox.open () == SWT.YES)
				    			shell.setEnabled(true);
				      }
				 });
				generateShell.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	}
	
	public static void main(String[] args) {
		presenter.Properties pr = new presenter.Properties();
		pr.setAlgorithmGenerateName("MyMaze3dGenerator");
		pr.setAlgorithmSearchName("BFS");
		pr.setNumOfThreads(10);
		pr.setxSize(5);
		pr.setySize(5);
		pr.setzSize(5);
		pr.setUserInterface("GUI");
		pr.setMazeName("mainMaze");
		MyModel m = new MyModel(pr);
		MenuWindow v=new MenuWindow("Menu", 500, 300);
		MyPresenter p = new MyPresenter(m,v);
		
		m.addObserver(p);
		v.addObserver(p);
		
		v.start();
		/*Integer i = new Integer("155");
		System.out.println(i);*/
	
	}

	@Override
	public void start() {
		run();
	}

	@Override
	public void printString(String s) {
		System.out.println(s);
	}

	@Override
	public void printMaze3d(int[][][] arr, String name) {
		// TODO Auto-generated method stub
		
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
	public void crossSectionPrint(int[][] arr, String sectionType, String name, String section) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isInt(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)<='0' || str.charAt(i)>='9')
				return false;
		}
		return true;
	}

}
