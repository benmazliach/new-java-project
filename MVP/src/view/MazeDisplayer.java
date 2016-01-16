package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public abstract class MazeDisplayer<T> extends Canvas{
	
	protected int[][] mazeData;
	protected MazeCharacter<Position> goalPosition;
	protected MazeCharacter<Position> character;
	protected boolean finish;
	
	public int[][] getMazeData() {
		return mazeData;
	}

	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
	}
	

	public abstract void moveUp(String str);

	public abstract  void moveDown(String str);

	public abstract  void moveLeft(String str);

	public  abstract void moveRight(String str);

	public abstract T getGoalPosition();
	
	public abstract void setGoalPosition(T goalPosition);
	
	public abstract T getCharacter();
	
	public abstract void setCharacter(T character);
	
	public abstract String[] possibleMoves(String str);
	
	public abstract void draw(String str);
	
	
	
	public abstract Solution<T> getSol();

	public abstract void setSol(Solution<T> sol);
	
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

}