package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class Maze3D extends MazeDisplayer {
	
	public int characterX=0;
	public int characterY=2;
	public int exitX=0;
	public int exitY=2;
	
	public String section;
	public Position startPosition;
	public Position goalPosition;
	public Position character;
	
	public Maze3D(Composite parent, int style) {
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
			 		for(int j=0;j<mazeData[i].length;j++){
			 			int x=j*w;
				        int y=i*h;
				        if(mazeData[i][j]!=0)
				            e.gc.fillRectangle(x,y,w,h);
				        else
				        {
				        	if(j==characterX&&i==characterY)
				        	{
				        		System.out.println(characterX+","+characterY);
				        		Image m = new Image(getDisplay(), "resources/pacman.png");
				        		e.gc.drawImage(m,0,0,m.getBounds().width,m.getBounds().height,x,y,w,h);
				        	}
				        }
				    }
				}
		});
	}
	
	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		characterX = startPosition.getpX();
	 	characterY = startPosition.getpZ();
	 	character = startPosition;
		this.startPosition = startPosition;
	}

	public Position getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}

	private void moveCharacter(int x,int y){
		if(x>=0 && x<mazeData[0].length && y>=0 && y<mazeData.length && mazeData[y][x]==0){
			{
				//System.out.println("b: "+characterX+","+characterY);
				characterX=x;
				characterY=y;
				character.setpX(x);
				character.setpZ(y);
				//System.out.println("a: "+characterX+","+characterY);
				getDisplay().syncExec(new Runnable() {
				
					@Override
					public void run() {
						redraw();
					}
				});
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveUp()
	 */
	@Override
	public void moveUp() {
		//System.out.println("up: "+characterX+","+characterY);
		int x=characterX;
		int y=characterY;
		y=y-1;
		moveCharacter(x, y);
	}
	public Position getCharacter() {
		return character;
	}

	public void setCharacter(Position character) {
		this.character = character;
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveDown()
	 */
	@Override
	public void moveDown() {
		//System.out.println("DOWN: "+characterX+","+characterY);
		int x=characterX;
		int y=characterY;
		y=y+1;
		moveCharacter(x, y);
	}
	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveLeft()
	 */
	@Override
	public void moveLeft() {
		//System.out.println("left: "+characterX+","+characterY);
		int x=characterX;
		int y=characterY;
		x=x-1;
		moveCharacter(x, y);
	}
	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveRight()
	 */
	@Override
	public void moveRight() {
		//System.out.println("Right: "+characterX+","+characterY);
		int x=characterX;
		int y=characterY;
		x=x+1;
		moveCharacter(x, y);
	}
	
	@Override
	public void setCharacterPosition(int row, int col) {
		characterX=col;
		characterY=row;
		moveCharacter(col,row);
	}
	
	public boolean posibleNextMove(int x,int y)
	{
		if(mazeData[x][y]==1)
			return false;
		else
			return true;
	}

	@Override
	public void setSection(String section) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution<Position> getSol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSol(Solution<Position> sol) {
		// TODO Auto-generated method stub
		
	}

}
