package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas{
	
	// just as a stub...
	int[][] mazeData;

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
	}
	

	public abstract void moveUp();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();
	
	public abstract Position getStartPosition() ;
	
	public abstract void setStartPosition(Position startPosition);

	public abstract Position getGoalPosition();
	
	public abstract void setGoalPosition(Position goalPosition);

	public abstract void setCharacterPosition(int row, int col);
	
	public abstract void setSection(String section);
	
	public abstract Position getCharacter();
	
	public abstract void setCharacter(Position character);

}