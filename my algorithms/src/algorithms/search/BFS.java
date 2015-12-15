package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import comperators.StateCostComparator;

/**
*<h1>  BFS class <h1>
* This class finds a path in the maze
* BFS is extends from CommonSearcher<T>
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class BFS<T> extends CommonSearcher<T> {
	
	/**
	 * Constructor that get StateCostComparator<T> 
	 * @param StateCostComparator<T>
	 */
	public BFS(StateCostComparator<T> c) {
		super(c);
	}

	/**
	 * This method find the best path
	 * He gets type of Searchable<T> and creates
	 * the path
	 * @param Searchable<T>
	 * @return Solution<T>
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		
		//BFS algorithm
		addToOpenList(s.getInitialState());
		HashSet<State<T>> closeSet = new HashSet<State<T>>();
		
		while(openList.size()>0)
		{
			State<T> n = popOpenList();//dequeue
			closeSet.add(n);
			
			if(n.equals(s.getGoalState()))
			{
				s.getGoalState().setCameFrom(n.getCameFrom());
				s.getGoalState().setCost(cost(s.getGoalState()));
				//Private method,back traces through the parents
				return backTrace(s.getGoalState(),s.getStartState());
			}
			
			ArrayList<State<T>> successors = s.getAllPossibleState(n);//However it is implemented
			for (State<T> state : successors) 
			{
				//System.out.println("c = " +closeSet.contains(state));
				//System.out.println("i = " +containsHashSet(state, closeSet));
				//if(!containsHashSet(state, closeSet) && !openListContains(state))
				if(!closeSet.contains(state) && !openListContains(state))
				{
					state.setCameFrom(n);
					addToOpenList(state);
				}
				else
				{
					if(betterPath(state))
					{
						if(!openListContains(state))
						{
							state.setCameFrom(n);
							addToOpenList(state);
						}
						else
						{
							openList.remove(returnSameState(state));
							state.setCameFrom(n);
							addToOpenList(state);
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * This method set the cost of the state<T>
	 * @param State<T>
	 * @return double
	 */
	protected double cost(State<T> pos)
	{
		if(pos.getCameFrom() == null) return 0 ;
		return pos.getCameFrom().getCost()+1;
	}

}
