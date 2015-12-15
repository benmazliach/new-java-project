package algorithms.search;
import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

/**
*<h1>  Maze3dDomain class <h1>
* This class implements Searchable, but use {@link Position}
* instead generic T
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class Maze3dDomain implements Searchable<Position> {

	private Maze3d maze;
	private State<Position> startPos;
	private State<Position> goalPos;
	
	/**
	 * Constructor that get Maze3d
	 * @param Maze3d
	 */
	public Maze3dDomain(Maze3d maze) {
		this.maze = maze;
		this.startPos = new State<Position>(maze.getStartPosition());
		this.startPos.setCameFrom(null);
		this.startPos.setCost(1);
		this.goalPos = new State<Position>(maze.getGoalPosition());
	}
	
	/**
	 * Override from the interface
	 * This method return the Initial State
	 * @return State<T>
	 */
	@Override
	public State<Position> getInitialState() {
		return startPos;
	}

	/**
	 * Override from the interface
	 * This method return the goal State
	 * @return State<T>
	 */
	@Override
	public State<Position> getGoalState() {
		return goalPos;
	}
	
	/**
	 * Override from the interface
	 * This method return the start State
	 * @return State<T>
	 */
	@Override
	public State<Position> getStartState() {
		return startPos;
	}

	/**
	 * This method return all possible moves
	 * from state
	 * @param State<T>
	 * @return ArrayList<State<T>>
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleState(State<Position> s) {
		Position[] arr = maze.getPossiblePositions(s.getState());
		ArrayList<State<Position>> allPos = new ArrayList<State<Position>>();
		State<Position> temp;
		for (Position pos : arr) {
			temp = new State<Position>();
			temp.setState(pos);
			temp.setCameFrom(s);
			temp.setCost(1);
			allPos.add(temp);
		}
		return allPos;
	}
	
	/**
	 * Get maze
	 * @return Maze3d
	 */
	//Getters & Setters
	public Maze3d getMaze() {
		return maze;
	}
	/**
	 * Set maze
	 * @param Maze3d
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	/**
	 * Get startState
	 * @return State<Position>
	 */
	public State<Position> getStartPos() {
		return startPos;
	}
	/**
	 * Set startState 
	 * @param State<Position>
	 */
	public void setStartPos(State<Position> startPos) {
		this.startPos = startPos;
	}
	/**
	 * Get goalState
	 * @return state<Position>
	 */
	public State<Position> getGoalPos() {
		return goalPos;
	}
	/**
	 * Set goalState
	 * @param State<Position>
	 */
	public void setGoalPos(State<Position> goalPos) {
		this.goalPos = goalPos;
	}

}
