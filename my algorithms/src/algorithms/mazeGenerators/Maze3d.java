package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Scanner;

/**
*<h1>  Maze3d class <h1>
*This class present 3 dimensions maze
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/

public class Maze3d{
	
	private int [][][] maze;
	private Position startPosition;
	private Position goalPosition;
	
	/**
	 * Constructor that creates Maze3d
	 * @param int x , int y , int z the sizes of the maze
	 */
	//Constructor
	//maze[y=Floors][x=Lines][z=Columns]
	public Maze3d(int x,int y,int z)
	{
		this.maze = new int[y][z][x];
		/*for(int i=0;i<y;i++)
		{
			for(int j=0;j<z;j++)
			{
				for(int k=0;k<x;k++)
				{
					maze[i][j][k] = 0;
				}
			}
		}*/
		
		/*
		//print
		for(dint i=0;i<y;i++)
		{
			for(dint j=0;j<z;j++)
			{
				for(dint k=0;k<x;k++)
				{
					System.out.print(checkValue(k, i, j));
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}*/
	}
	
	/**
	 * Constructor that creates Maze3d
	 * @param int[][][] Three-dimensional array
	 */
	//Constructor
	public Maze3d(int [][][] maze)
	{
		this.setMaze(maze);
	}
	
	/**
	 * Constructor that creates Maze3d
	 * The Constructor get array of bytes and create Maze3d
	 * @param byte[] arrayByte
	 */
	//Constructor
	public Maze3d(byte[] arrayByte)
	{
		setStartPosition(new Position((int)arrayByte[0], (int)arrayByte[1], (int)arrayByte[2]));
		setGoalPosition(new Position((int)arrayByte[3], (int)arrayByte[4], (int)arrayByte[5]));
		this.maze = new int[(int)arrayByte[7]][(int)arrayByte[8]][(int)arrayByte[6]];
		int index = 9;
		for(int i=0;i<getYSize();i++)
		{
			for(int j=0;j<getZSize();j++)
			{
				for(int k=0;k<getXSize();k++)
				{
					changeValue(k, i, j, arrayByte[index]);
					index++;
				}
			}
		}
	}

	/**
	 * Get maze 
	 * @return int[][][] Three-dimensional array
	 */
	//getter & setter maze
	public int [][][] getMaze() {
		return maze;
	}
	/**
	 * Set maze 
	 * @return void 
	 */
	public void setMaze(int [][][] maze) {
		this.maze = maze;
	}

	/**
	 * Get start position
	 * @return Position
	 */
	//getter & setter startPosition
	public Position getStartPosition() {
		return startPosition;
	}
	/**
	 * Set start position
	 * @param Position
	 */
	public void setStartPosition(Position p) {
		this.startPosition = p;
	}
	
	/**
	 * Get goal position
	 * @return Position
	 */
	//getter & setter goalPosition
	public Position getGoalPosition() {
		return goalPosition;
	}

	/**
	 * Set goal position
	 * @param Position
	 */
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	/**
	 * Gets the position value and return the value
	 * or -1
	 * @param int x, int y, int z
	 * @return int value
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	public int returnValue(int x,int y,int z)
	{
		if(maze.length>y && y>=0)
		{
			if(maze[y].length>z && z>=0)
			{
				if(maze[y][z].length>x && x>=0)
				{
					return maze[y][z][x];
				}
			}
		}
		return -1;
	}
	
	/**
	 * Get the position value and any value and change the value
	 * if the parameters are proper
	 * @param int x, int y, int z , int value
	 * @return int 
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	//משנה את ערך המקום במערך אם מקיים את התנאים הבאים. אם משנה את הערך הוא מחזיר 1, אם לא -1 
	public int changeValue(int x,int y,int z,int value)
	{
		if(maze.length>y && y>=0)
		{
			if(maze[y].length>z && z>=0)
			{
				if(maze[y][z].length>x && x>=0)
				{
					if((maze[y][z][x] == 1 || maze[y][z][x] == 0 ))
					{
						maze[y][z][x] = value;
						return 1;
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * Get maze size
	 * @return int
	 */
	public int getYSize()
	{
		return maze.length;
	}
	public int getZSize()
	{
		return maze[0].length;
	}
	public int getXSize()
	{
		return maze[0][0].length;
	}
	
	/**
	 * Get position and return all possible moves from this position
	 * @param Postion
	 * @return String[]
	 */
	public String[] getPossibleMoves(Position p)
	{
		String[] s = new String[6];
		int i=0;
		if(returnValue(p.getpX()+1, p.getpY(),p.getpZ())==0)
		{
			s[i] = "Right";
			i++;
		}
		if(returnValue(p.getpX()-1, p.getpY(),p.getpZ())==0)
		{
			s[i] = "Left";
			i++;
		}
		if(returnValue(p.getpX(), p.getpY()+1,p.getpZ())==0)
		{
			s[i] = "Up";
			i++;
		}
		if(returnValue(p.getpX(), p.getpY()-1,p.getpZ())==0)
		{
			s[i] = "Down";
			i++;
		}
		if(returnValue(p.getpX(), p.getpY(),p.getpZ()+1)==0)
		{
			s[i] = "Forward";
			i++;
		}
		if(returnValue(p.getpX(), p.getpY(),p.getpZ()-1)==0)
		{
			s[i] = "Backward";
			i++;
		}
		
		String[] s1 = new String[i];
		for(int j=0;j<i;j++)
			s1[j] = s[j];
		
		return s1;
	}
	
	/**
	 * Get position and return all possible Position from this position
	 * @param Postion
	 * @return Position[]
	 */
	public Position[] getPossiblePositions(Position p)
	{
		Position[] pos = new Position[6];
		int i=0;
		if(returnValue(p.getpX()+1, p.getpY(),p.getpZ())==0)
		{
			pos[i] = new Position(p.getpX()+1, p.getpY(),p.getpZ());
			i++;
		}
		if(returnValue(p.getpX()-1, p.getpY(),p.getpZ())==0)
		{
			pos[i] = new Position(p.getpX()-1, p.getpY(),p.getpZ());
			i++;
		}
		if(returnValue(p.getpX(), p.getpY()+1,p.getpZ())==0)
		{
			pos[i] = new Position(p.getpX(), p.getpY()+1,p.getpZ());
			i++;
		}
		if(returnValue(p.getpX(), p.getpY()-1,p.getpZ())==0)
		{
			pos[i] = new Position(p.getpX(), p.getpY()-1,p.getpZ());
			i++;
		}
		if(returnValue(p.getpX(), p.getpY(),p.getpZ()+1)==0)
		{
			pos[i] = new Position(p.getpX(), p.getpY(),p.getpZ()+1);
			i++;
		}
		if(returnValue(p.getpX(), p.getpY(),p.getpZ()-1)==0)
		{
			pos[i] = new Position(p.getpX(), p.getpY(),p.getpZ()-1);
			i++;
		}
		
		Position[] pos1 = new Position[i];
		for(int j=0;j<i;j++)
			pos1[j] = pos[j];
		
		return pos1;
	}
	
	/**
	 * Get x and return Two-dimensional array on this x
	 * @param int x
	 * @return int[][]
	 */
	public int[][] getCrossSectionByX(int x)
	{
		if(x>maze[0][0].length || x<0)
		{
			throw new IndexOutOfBoundsException("Failed");
		}
		int[][] temp = new int[maze.length][maze[0].length];
		for(int i=0;i<maze.length;i++)
		{
			for(int j=0;j<maze[0].length;j++)
			{
				temp[i][j] = maze[i][j][x];
			}
		}
		return temp;
	}
	
	/**
	 * Get y and return Two-dimensional array on this y
	 * @param int y
	 * @return int[][]
	 */
	public int[][] getCrossSectionByY(int y)
	{
		if(y>maze.length || y<0)
		{
			throw new IndexOutOfBoundsException("Failed");
		}
		int[][] temp = new int[maze[0].length][maze[0][0].length];
		for(int i=0;i<maze[0].length;i++)
		{
			for(int j=0;j<maze[0][0].length;j++)
			{
				temp[i][j] = maze[y][i][j];
			}
		}
		
		return temp;
	}
	
	/**
	 * Get y and return Two-dimensional array on this y
	 * @param int y
	 * @return int[][]
	 */
	public int[][] getCrossSectionByZ(int z)
	{	
		if(z>maze[0].length || z<0)
		{
			throw new IndexOutOfBoundsException("Failed");
		}
		int[][] temp = new int[maze.length][maze[0][0].length];
		for(int i=0;i<maze.length;i++)
		{
			for(int j=0;j<maze[0][0].length;j++)
			{
				temp[i][j] = maze[i][z][j];
			}
		}
		
		return temp;
	}
	
	/**
	 * Compress the maze to byte array
	 * @return byte[] 
	 */
	public byte[] toByteArray()
	{
		/*
		int size = getXSize() * getYSize() * getZSize() + 9;
		byte[] temp = new byte[size];
		//start position
		temp[0] = (byte) getStartPosition().getpX();
		temp[1] = (byte) getStartPosition().getpY();
		temp[2] = (byte) getStartPosition().getpZ();
		//goal position
		temp[3] = (byte) getGoalPosition().getpX();
		temp[4] = (byte) getGoalPosition().getpY();
		temp[5] = (byte) getGoalPosition().getpZ();
		//maze size
		temp[6] = (byte) getXSize();
		temp[7] = (byte) getYSize();
		temp[8] = (byte) getZSize();
		//maze data
		int index = 9;
		for(int i=0;i<getYSize();i++)
		{
			for(int j=0;j<getZSize();j++)
			{
				for(int k=0;k<getXSize();k++)
				{
					temp[index] = (byte) returnValue(k, i, j);
					index++;
				}
			}
		}
		return temp;
		*/
		ArrayList<Byte> byteMaze = new ArrayList<Byte>();
		
		//start position x,y,z
		byteMaze.add((new Integer(this.getStartPosition().getpX()).byteValue()));
		byteMaze.add((new Integer(this.getStartPosition().getpY()).byteValue()));
		byteMaze.add((new Integer(this.getStartPosition().getpZ()).byteValue()));
		//goal position x,y,z
		byteMaze.add((new Integer(this.getGoalPosition().getpX()).byteValue()));
		byteMaze.add((new Integer(this.getGoalPosition().getpY()).byteValue()));
		byteMaze.add((new Integer(this.getGoalPosition().getpZ()).byteValue()));
		//size x,y,z
		byteMaze.add((new Integer(this.maze[0][0].length).byteValue()));//x
		byteMaze.add((new Integer(this.maze.length).byteValue()));//y
		byteMaze.add((new Integer(this.maze[0].length).byteValue()));//z
		//maze data
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					byteMaze.add((new Integer(this.maze[i][j][k]).byteValue()));					
				}
			}
		}
		//Compress from ArrayList to byte array
		byte[] CommpressedMaze = new byte[byteMaze.size()];
		
		for (int i = 0; i < byteMaze.size(); i++) {
			CommpressedMaze[i] = byteMaze.get(i);
		}
		
		return CommpressedMaze;
	}
	
	/**
	 * Indicates maze3d is "equal to" this one.
	 * @param Maze3d m
	 * @return boolean
	 */
	public boolean equals(Maze3d m) {
		if(this.getXSize()!=m.getXSize()||this.getYSize()!=m.getYSize()||this.getZSize()!=m.getZSize())
			return false;
		for (int i = 0; i < this.getYSize(); i++) {
			for (int j = 0; j < this.getZSize(); j++) {
				for (int k = 0; k < this.getXSize(); k++) {
					if(this.returnValue(k, i, j)!=m.returnValue(k, i, j))
						return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Prints the maze
	 */
	public void printMaze()
	{
		System.out.println("the maze:");
		for (int i = 0; i < this.getYSize(); i++)
		{
			System.out.println("maze[" + i + "][][]: ");
			for (int j = 0; j < this.getZSize(); j++)
			{
				System.out.print("maze[" + i + "][" +j + "][]: ");
				for (int k = 0; k < this.getXSize(); k++)
				{
					System.out.print(returnValue(k, i, j) + " ");
				}
				System.out.println("");
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * changes the position value
	 * if the parameters are proper
	 * @param Position pos , int value
	 * @return int 
	 */
	public void setPositionValue(Position pos, int value)
	{//check!!
		Scanner s = new Scanner(System.in);
		
		while(!(value == 0 || value == 1))
		{
			System.out.println("enter:");
			value = s.nextInt();
		}		
			changeValue(this.getXSize(), this.getYSize(), this.getZSize(), value);
		s.close();
	}
	
}
