package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class Maze2D<T> extends MazeDisplayer<Position>{
	
	private Solution<Position> sol;
	private Image solutionImage;
	private Image winImage;
	private String section;
	
	public Maze2D(Composite parent, int style,Image goalI,Image characterI,Image solution,Image win){
		super(parent, style);
		
		setBackground(new Color(null, 255, 255, 255));
		
		this.goalPosition = new MazeCharacter<Position>(goalI);
		this.character = new MazeCharacter<Position>(characterI);
		this.solutionImage = solution;
		this.winImage = win;
	}
	
	public void draw(String sectionD)
	{
		final Color white=new Color(null, 255, 255, 255);
		
		section = sectionD;
		
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
			 	
			 	if(character.getCharacter().equals(goalPosition.getCharacter())==true)
			 	{
	        		e.gc.drawImage(winImage,0,0,winImage.getBounds().width,winImage.getBounds().height,0,0,width,height);
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
				        			if(sol.getSol().contains(new State<Position>(new Position(character.getCharacter().getpX(), i, j))) == true)
					        		{
				        				if(character.getCharacter().equals(new Position(character.getCharacter().getpX(), i, j))==true)
				        					sol.getSol().remove(new State<Position>(new Position(character.getCharacter().getpX(), i, j)));
						        		e.gc.drawImage(solutionImage,0,0,solutionImage.getBounds().width,solutionImage.getBounds().height,x,y,w,h);
					        		}
				        		}
				        		if(character.getCharacter().getpX()==goalPosition.getCharacter().getpX())
				        		{
				        			if(j==goalPosition.getCharacter().getpZ()&&i==goalPosition.getCharacter().getpY())
						        	{
				        				e.gc.drawImage(goalPosition.getImage(),0,0,goalPosition.getImage().getBounds().width,goalPosition.getImage().getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getCharacter().getpZ()&&i==character.getCharacter().getpY())
				        		{
					        		e.gc.drawImage(character.getImage(),0,0,character.getImage().getBounds().width,character.getImage().getBounds().height,x,y,w,h);
					        	}
				        	}
				        	else if(section.equals("y")==true)
				        	{
				        		if(sol!=null)
				        		{
				        			if(sol.getSol().contains(new State<Position>(new Position(j, character.getCharacter().getpY(), i))) == true)
					        		{
				        				if(character.getCharacter().equals(new Position(j, character.getCharacter().getpY(), i))==true)
				        					sol.getSol().remove(new State<Position>(new Position(j, character.getCharacter().getpY(), i)));
				        				e.gc.drawImage(solutionImage,0,0,solutionImage.getBounds().width,solutionImage.getBounds().height,x,y,w,h);
					        		}
				        		}
				        		if(character.getCharacter().getpY()==goalPosition.getCharacter().getpY())
				        		{
				        			if(j==goalPosition.getCharacter().getpX()&&i==goalPosition.getCharacter().getpZ())
						        	{
						        		e.gc.drawImage(goalPosition.getImage(),0,0,solutionImage.getBounds().width,solutionImage.getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getCharacter().getpX()&&i==character.getCharacter().getpZ())
					        	{
					        		e.gc.drawImage(character.getImage(),0,0,character.getImage().getBounds().width,character.getImage().getBounds().height,x,y,w,h);
					        	}
				        	}
				        	else if(section.equals("z")==true)
				        	{
				        		if(sol!=null)
				        		{
				        			if(sol.getSol().contains(new State<Position>(new Position(j, i, character.getCharacter().getpZ()))) == true)
					        		{
				        				if(character.getCharacter().equals(new Position(j, i, character.getCharacter().getpZ()))==true)
				        						sol.getSol().remove(new State<Position>(new Position(j, i, character.getCharacter().getpZ())));
						        		e.gc.drawImage(solutionImage,0,0,solutionImage.getBounds().width,solutionImage.getBounds().height,x,y,w,h);
					        		}
				        		}
				        		if(character.getCharacter().getpZ()==goalPosition.getCharacter().getpZ())
				        		{
				        			if(j==goalPosition.getCharacter().getpX()&&i==goalPosition.getCharacter().getpY())
						        	{
						        		e.gc.drawImage(goalPosition.getImage(),0,0,goalPosition.getImage().getBounds().width,goalPosition.getImage().getBounds().height,x,y,w,h);
						        	}
				        		}
				        		if(j==character.getCharacter().getpX()&&i==character.getCharacter().getpY())
					        	{
					        		e.gc.drawImage(character.getImage(),0,0,character.getImage().getBounds().width,character.getImage().getBounds().height,x,y,w,h);
					        	}
				        	}
				        }
				    }
				}
			}
		});
	}
	
	@Override
	public String[] possibleMoves(String section) {
		String s = "";
		int w = 0;
		int h = 0;
		
		if(section.equals("y")==true || section.equals(null))
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getCharacter().getpZ();
			h = character.getCharacter().getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpY();
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
	public void moveUp(String section) {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true || section.equals(null))
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getCharacter().getpZ();
			h = character.getCharacter().getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpY();
		}
		else
			return;
		h=h-1;
		moveCharacter(w, h,section);
	}

	@Override
	public void moveDown(String section) {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true || section.equals(null))
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getCharacter().getpZ();
			h = character.getCharacter().getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpY();
		}
		else
			return;
		h=h+1;
		moveCharacter(w, h,section);
	}

	@Override
	public void moveLeft(String section) {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true || section.equals(null))
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getCharacter().getpZ();
			h = character.getCharacter().getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpY();
		}
		else
			return;
		w=w-1;
		moveCharacter(w, h,section);
	}

	@Override
	public void moveRight(String section) {
		int w = 0;
		int h = 0;;
		if(section.equals("y")==true || section.equals(null))
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpZ();
		}
		else if(section.equals("x")==true)
		{
			w = character.getCharacter().getpZ();
			h = character.getCharacter().getpY();
		}
		else if(section.equals("z")==true)
		{
			w = character.getCharacter().getpX();
			h = character.getCharacter().getpY();
		}
		else
			return;
		w=w+1;
		moveCharacter(w, h,section);
	}
	
	private void moveCharacter(int w,int h,String section){
		if(w>=0 && w<mazeData[0].length && h>=0 && h<mazeData.length && mazeData[h][w]==0){
			{
				if(section.equals("y")==true || section.equals(null))
				{
					character.getCharacter().setpX(w);
					character.getCharacter().setpZ(h);
				}
				else if(section.equals("x")==true)
				{
					character.getCharacter().setpZ(w);
					character.getCharacter().setpY(h);
				}
				else if(section.equals("z")==true)
				{
					character.getCharacter().setpX(w);
					character.getCharacter().setpY(h);
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
	
	@Override
	public Position getGoalPosition() {
		return this.goalPosition.getCharacter();
	}

	@Override
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition.setCharacter(new Position(goalPosition.getpX(), goalPosition.getpY(), goalPosition.getpZ()));
	}
	
	public Position getCharacter() {
		return character.getCharacter();
	}

	public void setCharacter(Position character) {
		this.character.setCharacter(new Position(character.getpX(), character.getpY(), character.getpZ()));
	}
	
	public Solution<Position> getSol() {
		return sol;
	}

	public void setSol(Solution<Position> sol) {
		this.sol = sol;
	}

}
