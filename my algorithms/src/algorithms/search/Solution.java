package algorithms.search;

import java.util.ArrayList;

/**
*<h1>  Solution class <h1>
* This class define the solution to
* any search problem
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class Solution<T> {
	
	private ArrayList<State<T>> sol;

	/**
	 * Constructor that get ArrayList<State<T>>
	 * @param ArrayList<State<T>>
	 */
	public Solution(ArrayList<State<T>> sol){
		this.sol = sol;
	}
	
	/**
	 * Get arrayList
	 * @return ArrayList<State<T>>
	 */
	//Getter
	public ArrayList<State<T>> getSol() {
		return sol;
	}
	/**
	 * Set arrayList
	 * @param ArrayList<State<T>>
	 */
	//Setter
	public void setSol(ArrayList<State<T>> sol) {
		this.sol = sol;
	}

}
