package algorithms.search;

/**
*<h1>  Searcher interface <h1>
* This interface present any searcher
* that now to find path from the start state<T>
* to the goal State<T>
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public interface Searcher<T> {
	
	/**
	 * This method find the best path
	 * He gets type of Searchable<T> and creates
	 * the path
	 * @param Searchable<T>
	 * @return Solution<T>
	 */
	//The search method
	public Solution<T> search(Searchable<T> s);
	 
	/**
	 * This method return the number of nodes
	 * that we pop from openList(When we reach
	 * on any state that the cost and cameFrom are changed
	 * but its the same state we did not count this twice 
	 * @return Solution<T>
	 */
	
	//Get how many nodes were evaluated by the algorithm
	public int getNumberOfNodesEvaluated();

}
