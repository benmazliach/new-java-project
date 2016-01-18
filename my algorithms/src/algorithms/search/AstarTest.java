package algorithms.search;

import static org.junit.Assert.fail;
import java.util.ArrayList;
import org.junit.Test;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import comperators.StateCostComparator;


public class AstarTest {

	 State<Position> st  = new State<Position>(new Position(0, 1, 0));
	 State<Position> came_from  = new State<Position>(new Position(0, 1, 0));
	 Maze3d maze = new Maze3d(2, 2, 2);
	 int[][][] arr = { { {0,1} , {0,1} } ,{ {0,0} ,{0,1} } };
	 ArrayList<Position> sol = new ArrayList<Position>();
	 Astar<Position> astar;
	 Maze3dDomain se = new Maze3dDomain(maze);
	 
	 
	 public AstarTest() {
		  maze.setGoalPosition(new Position(1, 1, 0));
		  astar = new Astar<Position>(new StateCostComparator<Position>(), new MazeManhattanDistance(se));
		  maze.setStartPosition(new Position(0, 0, 0));
		  came_from.setCost(5); 
		  st.setCameFrom(came_from);
		  
		  sol.add(maze.getGoalPosition()) ;
	 }
	 
	 
	 @Test
	 public void testCalculateCost() {
	  
		  if(astar.cost(st)!=7.0)
		  fail("Not yet implemented");
	 }
	
	 @Test
	 public void testSearch() {
		  if(astar.search(se).getSol().equals(sol))
		  fail("Not yet implemented");
	 }

}