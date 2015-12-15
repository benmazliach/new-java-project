package algorithms.mazeGenerators;

import java.util.Random;

/**
*<h1> SimpleMaze3dGenerator class <h1>
* This class creates maze with simple algorithm
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {
	
	/**
	 * Default constructor
	 */
	public SimpleMaze3dGenerator(){}
	
	/**
	 * Constructor that creates Maze3d and start the generate
	 * method that find path
	 * @param int x , int y , int z the sizes of the maze
	 */
	public SimpleMaze3dGenerator(int x,int y,int z) { 
		maze = new Maze3d(x,y,z);
		generate(x, y, z);
	}
	
	
	/**
	 * Override generate method from Maze3dGenerator
	 * @param int x , int y , int z the sizes of the maze
	 * @return Maze3d the maze
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	@Override
	public Maze3d generate(int x, int y, int z) {
		
		if(maze==null)
			this.maze = new Maze3d(x,y,z);
		
		//Random selection of zero or one
		int rd;
		Random rand = new Random();
		for(int i=0;i<maze.getYSize();i++)
		{
			for(int j=0;j<maze.getZSize();j++)
			{
				for(int k=0;k<maze.getXSize();k++)
				{
					rd = rand.nextInt(2);
					maze.changeValue(k, i, j, rd);
				}
			}
		}
		
		//Choosing a start position values that we chose them with random
		int x1 = rand.nextInt(x); 
		int y1 = rand.nextInt(y); 
		int z1 = rand.nextInt(z);
		Position p =new Position(x1,y1,z1);
		maze.setStartPosition(p);
		maze.changeValue(x1, y1, z1, 0);
		
		//Choosing a goal position values that we chose them with random
		int x2 = rand.nextInt(x); 
		int y2 = rand.nextInt(y); 
		int z2 = rand.nextInt(z); 
		
		//We checks that the start position and the goal position are not same
		while((x1==x2)&&(y1==y2)&&(z1==z2))
		{
			x2 = rand.nextInt(x); 
			y2 = rand.nextInt(y); 
			z2 = rand.nextInt(z); 
		}
		p =new Position(x2,y2,z2);
		maze.setGoalPosition(p);
		maze.changeValue(x2, y2, z2, 0);
				
		//System.out.println("start : y = " + y1 + " z = " + z1 + " x = " + x1);		
		//System.out.println("goal : y = " + y2 + " z = " + z2 + " x = " + x2);
		
		//Finding the path
		if(x1<x2)
		{
			for(int i=x1;i<x2;i++)
			{
				maze.changeValue(i, y1, z1, 0);
				x1++;
			}
		}
		else
		{
			if(x1>x2)
			{
				for(int i=x1;i>x2;i--)
				{
					maze.changeValue(i, y1, z1, 0);
					x1--;
				}
			}
		}
		if(y1<y2)
		{
			for(int i=y1;i<y2;i++)
			{
				maze.changeValue(x1, i, z1, 0);
				y1++;
			}
		}
		else
		{
			if(y1>y2)
			{
				for(int i=y1;i>y2;i--)
				{
					maze.changeValue(x1, i, z1, 0);
					y1--;
				}
			}
		}
		if(z1<z2)
		{
			for(int i=z1;i<z2;i++)
			{
				maze.changeValue(x1, y1, i, 0);
				z1++;
			}
		}
		else
		{
			if(z1>z2)
			{
				for(int i=z1;i>z2;i--)
				{
					maze.changeValue(x1, y1, i, 0);
					z1--;
				}
			}
		}
		
		/*
		//print
		System.out.println("********************");
		System.out.println("simple:");
		for(dint i=0;i<y;i++)
		{
			for(dint j=0;j<z;j++)
			{
				for(dint k=0;k<x;k++)
				{
					System.out.print(maze.returnValue(k, i, j)+" ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("********************");*/
		
		return maze;
	}
}
