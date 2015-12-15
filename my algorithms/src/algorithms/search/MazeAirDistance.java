package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
*<h1>  MazeAirDistance class <h1>
* This class present specific Heuristic
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class MazeAirDistance extends CommonHeuristic<Position> {

	/**
	 * Constructor that get Searchable<Position> 
	 * Use CommonHeuristic constructor
	 * @param Searchable<T>
	 */
	public MazeAirDistance(Searchable<Position> s) {
		super(s);
	}
	
	/**
	* This method return evaluation to the path
	* We Override the original method 
	* @param State<T> 
	* @return double evaluation
	*/
	@Override
	public double heuristic(State<Position> state) {
		if(searchable!=null)
		{
			int x =  state.getState().getpX()-searchable.getGoalState().getState().getpX();
			if(x<0)
				x = (-1)*x;
			int y = state.getState().getpY()-searchable.getGoalState().getState().getpY();
			if(y<0)
				y = (-1)*y;
			int z = state.getState().getpZ()-searchable.getGoalState().getState().getpZ();
			if(z<0)
				z = (-1)*z;
			return Math.sqrt(x*x+y*y+z*z);
		}
		return 0;
	}

}
