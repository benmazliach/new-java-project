package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import comperators.StateCostComparator;

/**
*<h1>  CommonSearcher class <h1>
* This class implements Searcher<T> interface
* In this class we define general methods that match 
* to any Searcher class
* to the goal State<T>
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public abstract class CommonSearcher<T> implements Searcher<T> {

	//PriorityQueue
	protected PriorityQueue<State<T>> openList;
	//The number of evaluated nodes
	private int evaluatedNodes;

	/**
	 * Constructor that get StateCostComparator<T> 
	 * @param StateCostComparator<T>
	 */
	public CommonSearcher(StateCostComparator<T> c) {
		openList = new PriorityQueue<State<T>>(c);
		evaluatedNodes = 0;
	}
	
	/**
	 * Pop the top state
	 * @return State<T> the state with the lowest cost
	 */
	protected State<T> popOpenList()
	{
		evaluatedNodes++;
		return openList.poll();
	}
	/**
	 * Push/add state and set the cost
	 * @param State<T>
	 */
	protected void addToOpenList(State<T> pos) {
		if(pos.getCameFrom()!=null)
			pos.setCost(cost(pos));
		openList.add(pos);
	}
	
	/**
	 * This method set the cost of the state<T>
	 * @param State<T>
	 * @return double
	 */
	protected abstract double cost(State<T> pos);
	
	/**
	 * This method find the best path
	 * He gets type of Searchable<T> and creates
	 * the path
	 * @param Searchable<T>
	 * @return Solution<T>
	 */
	public abstract Solution<T> search(Searchable<T> s);

	/**
	 * Override the method from Searcher<T>
	 * This method return the number of nodes
	 * that we pop from openList(When we reach
	 * on any state that the cost and cameFrom are changed
	 * but its the same state we did not count this twice 
	 * @return int number of evaluated Nodes
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	/**
	 * This method return path form the goal to the start
	 * If we want the path from the start to goal we
	 * can use sort method
	 * @param State<T> goalState,State<T> startState
	 * @return Solution<T>
	 */
	public Solution<T> backTrace(State<T> goalState, State<T> startState) {
		State<T> state;
		ArrayList<State<T>> a = new ArrayList<State<T>>();
		a.add(goalState);
		state = goalState.getCameFrom();
		while(state!=null )
		{
			a.add(state);
			state = state.getCameFrom();	
		}
		Solution<T> sol = new Solution<T>(a);
		
		return sol;
	}

	/**
	 * This method return if the state in openList or not
	 * @param State<T> state
	 * @return boolean true if state in openList, else false
	 */
	protected boolean openListContains(State<T> state) {
		boolean in = false;
		for (State<T> stateOpenList : openList) {
			if(stateOpenList.equals(state))
				in = true;
		}
		return in;
	}
	
	/**
	 * This method return if the state in HashSet or not
	 * Like contains(We did not use it' its just for checking)
	 * @param State<T> state
	 * @return boolean true if state in HashSet, else false
	 */
	protected boolean containsHashSet(State<T> state,HashSet<State<T>> close) {
		boolean in = false;
		for (State<T> closeHash : close) {
			if(closeHash.equals(state))
				in = true;
		}
		return in;
	}
	
	/**
	 * This method return if the state openList and the costs are
	 * different
	 * @param State<T> state
	 * @return boolean true if state in HashSet, else false
	 */
	protected boolean betterPath(State<T> state) {
		boolean in = false;
		for (State<T> stateOpenList : openList) {
			if(stateOpenList.equals(state) && state.getCost()>stateOpenList.getCost())
				in = true;
		}
		return in;
	}
	
	/**
	 * This method return state if the state
	 * we get in openList and the costs are
	 * different
	 */
	protected State<T> returnSameState(State<T> state) {
		for (State<T> stateOpenList : openList) {
			if(stateOpenList.equals(state) && state.getCost()>stateOpenList.getCost())
			{
				evaluatedNodes--;
				return stateOpenList;
			}
		}
		return null;
	}
	

	/**
	 * Get EvaluatedNodes
	 * @return int
	 */
	//Getters & Setters
	public int getEvaluatedNodes() {
		return evaluatedNodes;
	}
	/**
	 * Set EvaluatedNodes
	 * @param int
	 */
	public void setEvaluatedNodes(int evaluatedNodes) {
		this.evaluatedNodes = evaluatedNodes;
	}
	
	/**
	 * Override method hashCode
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}
}
