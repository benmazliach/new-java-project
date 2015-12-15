package algorithms.search;

/**
*<h1>  Heuristic interface <h1>
* This interface help evaluated the best path
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/

public interface Heuristic<T> {
	
	/**
	* This method return evaluation to the path
	* @param State<T> 
	* @return double evaluation
	*/
	 double heuristic(State<T> state);
}
