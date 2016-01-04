package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

public class Maze2D extends MazeDisplayer{

	/*public int characterW;
	public int characterH;*/
	
	public String section;
	public Position startPosition;
	public Position goalPosition;
	public Position character;
	

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
				
				e.gc.setForeground(new Color(null,0,0,0));
				e.gc.setBackground(new Color(null,0,0,0));

				int width=getSize().x;
				int height=getSize().y;

				int w=width/mazeData[0].length;
			 	int h=height/mazeData.length;
				
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
				        		if(character.getpX()==goalPosition.getpX())
				        		{
				        			if(j==goalPosition.getpZ()&&i==goalPosition.getpY())
						        	{
				        				Image m = new Image(getDisplay(), "resources/goalPos.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getpZ()&&i==character.getpY())
					        	{
					        		Image m = new Image(getDisplay(), "resources/pacman.png");
					        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        	}
				        	}
				        	else if(section.equals("y")==true)
				        	{
				        		if(character.getpY()==goalPosition.getpY())
				        		{
				        			if(j==goalPosition.getpX()&&i==goalPosition.getpZ())
						        	{
				        				Image m = new Image(getDisplay(), "resources/goalPos.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getpX()&&i==character.getpZ())
					        	{
					        		Image m = new Image(getDisplay(), "resources/pacman.png");
					        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
					        	}
				        	}
				        	else if(section.equals("z")==true)
				        	{
				        		if(character.getpZ()==goalPosition.getpZ())
				        		{
				        			if(j==goalPosition.getpX()&&i==goalPosition.getpY())
						        	{
				        				Image m = new Image(getDisplay(), "resources/goalPos.jpg");
						        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getpX()&&i==character.getpY())
					        	{
					        		Image m = new Image(getDisplay(), "resources/pacman.png");
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
		w=w-1;
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
		this.startPosition = startPosition;
	}

	@Override
	public Position getGoalPosition() {
		return this.goalPosition;
	}

	@Override
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}

	@Override
	public void setCharacterPosition(int row, int col) {
	/*	characterX=col;
		characterY=row;
		moveCharacter(col,row);*/
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

	/*public int getCharacterX() {
		return characterX;
	}

	public void setCharacterX(int characterX) {
		this.characterX = characterX;
	}

	public int getCharacterY() {
		return characterY;
	}

	public void setCharacterY(int characterY) {
		this.characterY = characterY;
	}*/

	public Position getCharacter() {
		return character;
	}

	public void setCharacter(Position character) {
		this.character = character;
	}
	
}
