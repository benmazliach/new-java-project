package comperators;

import java.util.Comparator;
import algorithms.search.State;

/**
*<h1>  StateCostComparator class <h1>
* This class compare by the states cost
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class StateCostComparator<T> implements Comparator<State<T>> {

	/**
	* Override compare
	* This method compare between 2 states
	* @param State<T> 
	* @return double evaluation
	*/
	@Override
	public int compare(State<T> st1, State<T> st2) {
		if(st1.getCost()>st2.getCost())
			return 1;
		else if(st1.getCost()<st2.getCost())
			return -1;
		return 0;
	}

}
