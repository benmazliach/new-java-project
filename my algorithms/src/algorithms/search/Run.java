package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import comperators.StateCostComparator;

public class Run {

	public static void main(String[] args) {
		
		Maze3dGenerator maze = new MyMaze3dGenerator();
		maze.generate(8,1,8);
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
		
		System.out.println("start : y = " + maze.getStartPos());		
		System.out.println("goal : y = " + maze.getGoalPos());
		System.out.println();
		
		Searchable<Position> searchable = new Maze3dDomain(maze.getMaze());
		Searcher<Position> searcher = new BFS<Position>(new StateCostComparator<Position>());
		Solution<Position> s = searcher.search(searchable);
		ArrayList<State<Position>> a = s.getSol();
		System.out.println("BFS :");
		System.out.println("evaluatedNodes = " +searcher.getNumberOfNodesEvaluated() );
		for (State<Position> state : a) {
			System.out.println(state + " " + state.getCost());
		}
		
		Searcher<Position> searcher1 = new Astar<Position>(new StateCostComparator<Position>(), new MazeManhattanDistance(searchable));
		Solution<Position> s1 = searcher1.search(searchable);
		ArrayList<State<Position>> a1 = s1.getSol();
		System.out.println("Astar MazeManhattanDistance:");
		System.out.println("evaluatedNodes = " +searcher1.getNumberOfNodesEvaluated() );
		for (State<Position> state : a1) {
			System.out.println(state + " " + state.getCost());
		}
		
		Searcher<Position> searcher2 = new Astar<Position>(new StateCostComparator<Position>(), new MazeAirDistance(searchable));
		Solution<Position> s2 = searcher2.search(searchable);
		ArrayList<State<Position>> a2 = s2.getSol();
		System.out.println("Astar MazeAirDistance:");
		System.out.println("evaluatedNodes = " +searcher2.getNumberOfNodesEvaluated() );
		for (State<Position> state : a2) {
			System.out.println(state + " " +state.getCost());
		}
	}

}
