package algorithms.search;

import java.util.ArrayList;

import algorithms.search.State;;

/**
*<h1>  Searchable interface <h1>
* This interface finds define general Searchable
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public interface Searchable<T> {
	
	/**
	 * This method return the Initial State
	 * @return State<T>
	 */
	State<T> getInitialState();
	/**
	 * This method return the goal State
	 * @return State<T>
	 */
	State<T> getGoalState();
	/**
	 * This method return all possible moves
	 * from state
	 * @param State<T>
	 * @return ArrayList<State<T>>
	 */
	ArrayList<State<T>> getAllPossibleState(State<T> s);
	/**
	 * This method return the start State
	 * @return State<T>
	 */
	State<T> getStartState();

}
