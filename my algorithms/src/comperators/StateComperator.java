package comperators;

import algorithms.search.State;

/**
*<h1>  StateComperator interface <h1>
* This interface help define Comparator
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public interface StateComperator<T> {

	/**
	* This method compare between 2 states
	* @param State<T> 
	* @return double evaluation
	*/
	int compare(State<T> s1,State<T> s2);
	
}
