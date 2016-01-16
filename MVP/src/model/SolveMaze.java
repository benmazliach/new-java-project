package model;

import java.util.Observable;
import java.util.concurrent.Callable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Solution;
import comperators.StateCostComparator;

public class SolveMaze extends Observable {
	
	public Callable<Solution<Position>>  solve(String[] args, Maze3d maze)
	{
		Callable<Solution<Position>> call = new Callable<Solution<Position>>() {
			@Override
			public Solution<Position> call() {
				Searchable<Position> s = new Maze3dDomain(maze);
				BFS<Position> solve = null;
				Solution<Position> sol = null;
				if(args.length>=3 && args.length<5)
				{
					if(args[2].equals("BFS")==true)
					{
						solve = new BFS<Position>(new StateCostComparator<Position>());
						sol = new Solution<Position>(solve.search(s).getSol());
						notifyS("solution for " +args[1]+ " is ready");
					}
					else
						notifyS("Algorithm is not exist");
				}
				else if(args.length>=5)
				{
					if((args[2]+" "+args[3]+" "+args[4]).equals("Astar Air Distance")==true)
					{
						solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeAirDistance(s));
						sol = new Solution<Position>(solve.search(s).getSol());
						notifyS("solution for " +args[1]+ " is ready");
					}
					else if((args[2]+" "+args[3]+" "+args[4]).equals("Astar Manhattan Distance")==true)
					{
						solve = new Astar<Position>(new StateCostComparator<Position>(),new MazeManhattanDistance(s));
						sol = new Solution<Position>(solve.search(s).getSol());
						notifyS("solution for " +args[1]+ " is ready");
					}
					else
						notifyS("Algorithm is not exist");
				}
				else
					notifyS("Algorithm is not exist");
				return sol;
			}
		};
		return call;
	}

	private void notifyS(String str)
	{
		this.setChanged();
		notifyObservers(str);
	}

}