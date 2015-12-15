package algorithms.search;

/**
*<h1>  CommonHeuristic class <h1>
* This class present CommonHeuristic
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public abstract class CommonHeuristic<T> implements Heuristic<T> {

	protected Searchable<T> searchable;
	
	/**
	* This abstract method return evaluation to the path
	* @param State<T> 
	* @return double evaluation
	*/
	public abstract double heuristic(State<T> state);
	/**
	 * Constructor that get Searchable<T> 
	 * @param Searchable<T>
	 */
	public CommonHeuristic(Searchable<T> s)
	{
		this.searchable = s;
	}
}
