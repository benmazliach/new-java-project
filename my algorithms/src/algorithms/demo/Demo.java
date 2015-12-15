package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import comperators.StateCostComparator;
import algorithms.search.Astar;;

/**
*<h1>  Demo class <h1>
*This class present run function that 
*creates an object adapter that performs an 
*adaptation from Maze3d to Searchable.
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/

public class Demo {
	//ben
	/**
	* This method get x,y,z
	*
	*@param int x,int y, int z
	*/
	public void run(int lines, int floors, int columns)
	{
		//Creat Maze3dGeneretor
		Maze3dGenerator maze = new MyMaze3dGenerator();
		maze.generate(lines,floors,columns);
		//Print the maze
		System.out.println("The maze :");
		for(int y=0;y<maze.getYs();y++)
		{
			for(int z=0;z<maze.getZs();z++)
			{
				for(int x=0;x<maze.getXs();x++)
					System.out.print(maze.getValuePos(x, y, z)+" ");
				System.out.println();
			}
			System.out.println();
		}
		
		//Print start and goal positions
		System.out.println("start : " + maze.getStartPos());		
		System.out.println("goal : " + maze.getGoalPos());
		System.out.println();
		
		//Linking searchable and Searcher
		Searchable<Position> searchable = new Maze3dDomain(maze.getMaze());
		Searcher<Position> searcher = new BFS<Position>(new StateCostComparator<Position>());
		/**
		 * Start search method
		 * @param Searchable<Position>
		 * @return Solution<Position>
		 */
		Solution<Position> s = searcher.search(searchable);
		ArrayList<State<Position>> a = s.getSol();
		//a.sort(new StateCostComparator<Position>());
		
		//Print number of evaluatedNodes , the path & the cast of each node 
		System.out.println("BFS :");
		System.out.println("evaluatedNodes = " +searcher.getNumberOfNodesEvaluated() );
		for (State<Position> state : a) {
			System.out.println(state + " " + state.getCost());
		}
		
		//Linking searchable and Searcher
		Searcher<Position> searcher1 = new Astar<Position>(new StateCostComparator<Position>(), new MazeManhattanDistance(searchable));
		/**
		 * Start search method
		 * @param Searchable<Position>
		 * @return Solution<Position>
		 */
		Solution<Position> s1 = searcher1.search(searchable);
		ArrayList<State<Position>> a1 = s1.getSol();
		//a.sort(new StateCostComparator<Position>());
		
		//Print number of evaluatedNodes , the path & the cast of each node 
		System.out.println("Astar MazeManhattanDistance:");
		System.out.println("evaluatedNodes = " +searcher1.getNumberOfNodesEvaluated() );
		for (State<Position> state : a1) {
			System.out.println(state + " " + state.getCost());
		}
		
		//Linking searchable and Searcher
		Searcher<Position> searcher2 = new Astar<Position>(new StateCostComparator<Position>(), new MazeAirDistance(searchable));
		/**
		 * Start search method
		 * @param Searchable<Position>
		 * @return Solution<Position>
		 */
		Solution<Position> s2 = searcher2.search(searchable);
		ArrayList<State<Position>> a2 = s2.getSol();
		//a.sort(new StateCostComparator<Position>());
		
		//Print number of evaluatedNodes , the path & the cast of each node 
		System.out.println("Astar MazeAirDistance:");
		System.out.println("evaluatedNodes = " +searcher2.getNumberOfNodesEvaluated() );
		for (State<Position> state : a2) {
			System.out.println(state + " " +state.getCost());
		}
		
	}

}
