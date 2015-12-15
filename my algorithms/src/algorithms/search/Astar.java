package algorithms.search;

import comperators.StateCostComparator;

/**
*<h1>  Astar class <h1>
* This class finds a path in the maze
* Astar is type of BFS
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class Astar<T> extends BFS<T> {

	Heuristic<T> h;
	
	/**
	 * Constructor that get StateCostComparator<T> 
	 * @param StateCostComparator<T>
	 */
	public Astar(StateCostComparator<T> c) {
		super(c);
	}
	/**
	 * Constructor that get StateCostComparator<T> 
	 * and Heuristic<T>. this is the type of search
	 * @param StateCostComparator<T> ,Heuristic<T>
	 */
	public Astar(StateCostComparator<T> c,Heuristic<T> h) {
		super(c);
		this.h = h; 
	}
	
	
	/**
	 * Override the method cost from BFS
	 * This method set the cost of the state<T>
	 * with any heuristic<T>
	 * @param State<T>
	 * @return double
	 */
	@Override
	protected double cost(State<T> pos)
	{
		//f+h
		return super.cost(pos) + h.heuristic(pos);
	}

}
