package view;

import java.util.ArrayList;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class Maze2D extends MazeDisplayer{

	/*public int characterW;
	public int characterH;*/
	
	private String section;
	private Position startPosition;
	private Position goalPosition;
	private Position character;
	private Solution<Position> sol;
	private boolean finish;
	
	
	//תנזה להבין איך מפרידים את החייל מהמשחק שכאילו בתוך מחלקה זו לא יהיה את כל החלק של התמונות הספציפיות האלה כדי שהמבוך יהיה יור גינרי

	public Maze2D(Composite parent, int style) {
		super(parent, style);
		setBackground(new Color(null, 255, 255, 255));
		
		final Color white=new Color(null, 255, 255, 255);
		final Color black=new Color(null, 150,150,150);	
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				if(mazeData==null)
				{
					setBackground(white);
					return;
				}
				
				Image m = null;
				
				e.gc.setForeground(new Color(null,0,0,0));
				e.gc.setBackground(new Color(null,0,0,0));

				int width=getSize().x;
				int height=getSize().y;

				int w=width/mazeData[0].length;
			 	int h=height/mazeData.length;
			 	
			 	if(character.equals(goalPosition)==true)
			 	{
			 		m = new Image(getDisplay(), "resources/Treasure.jpg");
	        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,0,0,width,height);
	        		finish = true;
	        		return;
			 	}
				
			 	for(int i=0;i<mazeData.length;i++)
			 	{
			 		for(int j=0;j<mazeData[i].length;j++){
			 			int x=j*w;
				        int y=i*h;
				        if(mazeData[i][j]!=0)
				            e.gc.fillRectangle(x,y,w,h);
				        else
				        {
				        	if(section.equals("x")==true)
				        	{
				        		if(sol!=null)
				        		{
				        			if(sol.getSol().contains(new State<Position>(new Position(character.getpX(), i, j))) == true)
					        		{
				        				if(character.equals(new Position(character.getpX(), i, j))==true)
				        					sol.getSol().remove(new State<Position>(new Position(character.getpX(), i, j)));
				        				m = new Image(getDisplay(), "resources/coin.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        		}
				        		}
				        		if(character.getpX()==goalPosition.getpX())
				        		{
				        			if(j==goalPosition.getpZ()&&i==goalPosition.getpY())
						        	{
				        				m = new Image(getDisplay(), "resources/Treasure.jpg");
				        				e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getpZ()&&i==character.getpY())
				        		{
					        		m = new Image(getDisplay(), "resources/piratesIm.jpg");
					        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        	}
				        	}
				        	else if(section.equals("y")==true)
				        	{
				        		if(sol!=null)
				        		{
				        			if(sol.getSol().contains(new State<Position>(new Position(j, character.getpY(), i))) == true)
					        		{
				        				if(character.equals(new Position(j, character.getpY(), i))==true)
				        					sol.getSol().remove(new State<Position>(new Position(j, character.getpY(), i)));
				        				m = new Image(getDisplay(), "resources/coin.jpg");
				        				e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        		}
				        		}
				        		if(character.getpY()==goalPosition.getpY())
				        		{
				        			if(j==goalPosition.getpX()&&i==goalPosition.getpZ())
						        	{
				        				m = new Image(getDisplay(), "resources/goalPos.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getpX()&&i==character.getpZ())
					        	{
					        		m = new Image(getDisplay(), "resources/piratesIm.jpg");
					        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        	}
				        	}
				        	else if(section.equals("z")==true)
				        	{
				        		if(sol!=null)
				        		{
				        			if(sol.getSol().contains(new State<Position>(new Position(j, i, character.getpZ()))) == true)
					        		{
				        				if(character.equals(new Position(j, i, character.getpZ()))==true)
				        						sol.getSol().remove(new State<Position>(new Position(j, i, character.getpZ())));
				        				m = new Image(getDisplay(), "resources/coin.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        		}
				        		}
				        		if(character.getpZ()==goalPosition.getpZ())
				        		{
				        			if(j==goalPosition.getpX()&&i==goalPosition.getpY())
						        	{
				        				m = new Image(getDisplay(), "resources/goalPos.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getpX()&&i==character.getpY())
					        	{
					        		m = new Image(getDisplay(), "resources/piratesIm.jpg");
					        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        	}
				        	}
				        }
				    }
				}
			}
		});
	}
	
	@Override
	public String[] possibleMoves() {
		String s = "";
		int w = 0;
		int h = 0;
		
		if(section.equals("y")==true)
		{
			w = character.getpX();
			h = character.getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getpZ();
			h = character.getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getpX();
			h = character.getpY();
		}
		
		if(mazeData[0].length>(w+1))
			if(mazeData[h][w+1]==0)
				s+="Right ";
		if((w-1)>=0)
			if(mazeData[h][w-1]==0)
				s+="Left ";
		if(mazeData.length>(h+1))
			if(mazeData[h+1][w]==0)
				s+="Forward ";
		if((h-1)>=0)
			if(mazeData[h-1][w]==0)
				s+="Backward ";
		return s.split(" ");
	}
	
	@Override
	public void moveUp() {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true)
		{
			w = character.getpX();
			h = character.getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getpZ();
			h = character.getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getpX();
			h = character.getpY();
		}
		else
			return;
		h=h-1;
		moveCharacter(w, h);
	}

	@Override
	public void moveDown() {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true)
		{
			w = character.getpX();
			h = character.getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getpZ();
			h = character.getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getpX();
			h = character.getpY();
		}
		else
			return;
		h=h+1;
		moveCharacter(w, h);
	}

	@Override
	public void moveLeft() {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true)
		{
			w = character.getpX();
			h = character.getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getpZ();
			h = character.getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getpX();
			h = character.getpY();
		}
		else
			return;
		w=w-1;
		moveCharacter(w, h);
	}

	@Override
	public void moveRight() {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true)
		{
			w = character.getpX();
			h = character.getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getpZ();
			h = character.getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getpX();
			h = character.getpY();
		}
		else
			return;
		w=w+1;
		moveCharacter(w, h);
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	@Override
	public Position getStartPosition() {
		return startPosition;
	}

	@Override
	public void setStartPosition(Position startPosition) {
		this.startPosition = new Position(startPosition.getpX(), startPosition.getpY(), startPosition.getpZ());
	}

	@Override
	public Position getGoalPosition() {
		return this.goalPosition;
	}

	@Override
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = new Position(goalPosition.getpX(), goalPosition.getpY(), goalPosition.getpZ());
	}
	
	private void moveCharacter(int w,int h){
		if(w>=0 && w<mazeData[0].length && h>=0 && h<mazeData.length && mazeData[h][w]==0){
			{
				if(section.equals("y")==true)
				{
					character.setpX(w);
					character.setpZ(h);
				}
				else if(section.equals("x")==true)
				{
					character.setpZ(w);
					character.setpY(h);
				}
				else if(section.equals("z")==true)
				{
					character.setpX(w);
					character.setpY(h);
				}
				getDisplay().syncExec(new Runnable() {
				
					@Override
					public void run() {
						redraw();
					}
				});
			}
		}
	}
	
	public Position getCharacter() {
		return character;
	}

	public void setCharacter(Position character) {
		this.character = new Position(character.getpX(), character.getpY(), character.getpZ());
	}
	
	public Solution<Position> getSol() {
		return sol;
	}

	public void setSol(Solution<Position> sol) {
		this.sol = sol;
	}
	
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

}
