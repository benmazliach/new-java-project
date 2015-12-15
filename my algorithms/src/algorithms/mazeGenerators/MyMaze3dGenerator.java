package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
*<h1> MyMaze3dGenerator class <h1>
* This class creates maze with prim algorithm
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/

public class MyMaze3dGenerator extends CommonMaze3dGenerator{

	/**
	 * Default constructor
	 */
	public MyMaze3dGenerator() {}
	
	/**
	 * Constructor that creates Maze3d and start the generate
	 * method that find path
	 * @param int x , int y , int z the sizes of the maze
	 */
	public MyMaze3dGenerator(int x,int y,int z) { 
		maze = new Maze3d(x,y,z);
		generate(x, y, z);
	}
	
	/**
	 * Override generate method from Maze3dGenerator 
	 * Prim algorithm
	 * @param int x , int y , int z the sizes of the maze
	 * @return Maze3d the maze
	 */
	@Override
	public Maze3d generate(int x, int y, int z) {
		//Prim
		if(maze==null)
			maze = new Maze3d(x,y,z);
		//Prints maze is all one
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<z;j++)
			{
				for(int k=0;k<x;k++)
				{
					maze.changeValue(k, i, j, 1);
				}
			}
		}
		
		//Choosing a start position values that we chose them with random
		Random rand = new Random();
		int x1 = rand.nextInt(x); 
		int y1 = rand.nextInt(y); 
		int z1 = rand.nextInt(z);
		Position p =new Position(x1,y1,z1);
		maze.setStartPosition(p);
		maze.changeValue(x1, y1, z1, 0);
						
		//System.out.println(" start : x = " + x1 + " y = " + y1 + " z = " + z1);		
				
		//Made 2 ArrayList<Position> 
		//In wallList we put the walls that next to the current position
		ArrayList<Position> wallList = new ArrayList<Position>();
		ArrayList<Position> wallNeighboursList = new ArrayList<Position>();
		
		if(x1<(x-2))
		{
			p = new Position(x1+1,y1,z1);
			wallList.add(p);
			p = new Position(x1+2,y1,z1);
			wallNeighboursList.add(p);
		}
		if(x1>1)
		{
			p = new Position(x1-1,y1,z1);
			wallList.add(p);
			p = new Position(x1-2,y1,z1);
			wallNeighboursList.add(p);
		}
		if(y1<(y-2))
		{
			p = new Position(x1,y1+1,z1);
			wallList.add(p);
			p = new Position(x1,y1+2,z1);
			wallNeighboursList.add(p);
		}
		if(y1>1)
		{
			p = new Position(x1,y1-1,z1);
			wallList.add(p);
			p = new Position(x1,y1-2,z1);
			wallNeighboursList.add(p);
		}
		if(z1<(z-2))
		{
			p = new Position(x1,y1,z1+1);
			wallList.add(p);
			p = new Position(x1,y1,z1+2);
			wallNeighboursList.add(p);
		}
		if(z1>1)
		{
			p = new Position(x1,y1,z1-1);
			wallList.add(p);
			p = new Position(x1,y1,z1-2);
			wallNeighboursList.add(p);
		}
		
		int rd=0;
		Position p1,p2;
		
		//Search the best paths
		while(!wallList.isEmpty() && !wallNeighboursList.isEmpty())
		{
			rd = rand.nextInt(wallList.size());
			p1 = wallList.get(rd);
			p2 = wallNeighboursList.get(rd);
			//System.out.println("p1 = " + p1);
			//System.out.println("p2 = " + p2);
			if(maze.returnValue(p1.getpX(), p1.getpY(), p1.getpZ())==1 && maze.returnValue(p2.getpX(), p2.getpY(), p2.getpZ())==1)
			{
				maze.changeValue(p1.getpX(), p1.getpY(), p1.getpZ(), 0);
				maze.changeValue(p2.getpX(), p2.getpY(), p2.getpZ(), 0);
				if(p2.getpX()<(x-2))
				{
					if(maze.returnValue(p2.getpX()+1,p2.getpY(),p2.getpZ())==1 && maze.returnValue(p2.getpX()+2,p2.getpY(),p2.getpZ())==1)
					{
						p = new Position(p2.getpX()+1,p2.getpY(),p2.getpZ());
						wallList.add(p);
						p = new Position(p2.getpX()+2,p2.getpY(),p2.getpZ());
						wallNeighboursList.add(p);
					}
				}
				if(p2.getpX()>1)
				{
					if(maze.returnValue(p2.getpX()-1,p2.getpY(),p2.getpZ())==1 && maze.returnValue(p2.getpX()-2,p2.getpY(),p2.getpZ())==1)
					{
						p = new Position(p2.getpX()-1,p2.getpY(),p2.getpZ());
						wallList.add(p);
						p = new Position(p2.getpX()-2,p2.getpY(),p2.getpZ());
						wallNeighboursList.add(p);
					}
				}
				if(p1.getpY()<(y-2))
				{
					
					if(maze.returnValue(p2.getpX(),p2.getpY()+1,p2.getpZ())==1 && maze.returnValue(p2.getpX(),p2.getpY()+2,p2.getpZ())==1)
					{
						p = new Position(p2.getpX(),p2.getpY()+1,p2.getpZ());
						wallList.add(p);
						p = new Position(p2.getpX(),p2.getpY()+2,p2.getpZ());
						wallNeighboursList.add(p);
					}
				}
				if(p1.getpY()>1)
				{
					if(maze.returnValue(p2.getpX(),p2.getpY()-1,p2.getpZ())==1 && maze.returnValue(p2.getpX(),p2.getpY()-2,p2.getpZ())==1)
					{
						p = new Position(p2.getpX(),p2.getpY()-1,p2.getpZ());
						wallList.add(p);
						p = new Position(p2.getpX(),p2.getpY()-2,p2.getpZ());
						wallNeighboursList.add(p);
					}
				}
				if(p1.getpZ()<(z-2))
				{
					if(maze.returnValue(p2.getpX(),p2.getpY(),p2.getpZ()+1)==1 && maze.returnValue(p2.getpX(),p2.getpY(),p2.getpZ()+2)==1)
					{
						p = new Position(p2.getpX(),p2.getpY(),p2.getpZ()+1);
						wallList.add(p);
						p = new Position(p2.getpX(),p2.getpY(),p2.getpZ()+2);
						wallNeighboursList.add(p);
					}
				}
				if(p1.getpZ()>1)
				{				
					if(maze.returnValue(p2.getpX(),p2.getpY(),p2.getpZ()-1)==1 && maze.returnValue(p2.getpX(),p2.getpY(),p2.getpZ()-2)==1)
					{
						p = new Position(p2.getpX(),p2.getpY(),p2.getpZ()-1);
						wallList.add(p);
						p = new Position(p2.getpX(),p2.getpY(),p2.getpZ()-2);
						wallNeighboursList.add(p);
					}
				}
			}
			wallList.remove(rd);
			wallNeighboursList.remove(rd);
		}
		
		//Choosing a goal position values that we chose them with random
		rand = new Random();
		int x2 = rand.nextInt(x); 
		int y2 = rand.nextInt(y); 
		int z2 = rand.nextInt(z);
		while((x1==x2 && y1==y2 && z1==z2) || maze.returnValue(x2, y2, z2)==1)
		{
			x2 = rand.nextInt(x); 
			y2 = rand.nextInt(y); 
			z2 = rand.nextInt(z);
		}
		p =new Position(x2,y2,z2);
		maze.setGoalPosition(p);
		
		/*
		//print
		System.out.println("********************");
		System.out.println();
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<z;j++)
			{
				for(int k=0;k<x;k++)
				{
					System.out.print(maze.returnValue(k, i, j)+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("********************");
		*/
		
		//System.out.println("start : y = " + y1 + " z = " + z1 + " x = " + x1);		
		//System.out.println("goal : y = " + y2 + " z = " + z2 + " x = " + x2);
		
		return maze;
	}
}