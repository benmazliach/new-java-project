package view;
import java.util.Observable;
/**
 * <h1>  MyView class <h1>
 * This class define methods for each view system
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public abstract class CommonView extends Observable implements View {

	String[] args;
	
	/**
	 * start the view
	 */
	public abstract void start();
	/**
	 * print any string 
	 * @param String s
	 */
	public abstract void printString(String s);
	/**
	 * print maze3d
	 * @param int[][][],String - the maze,maze name
	 */
	public abstract void printMaze3d(int[][][] arr, String name);
	/**
	 * print cross by sectionType
	 * @param int[][],char,String,int - the cross maze,section type,maze name,section index
	 */
	public void setCommand(String[] args)
	{
		this.args = args;
		this.setChanged();
		notifyObservers();
	}

}
