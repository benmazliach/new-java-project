package algorithms.search;

/**
*<h1>  State class <h1>
* This class define state
* This class is Comparable<State<T>>
* 
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/
public class State<T> implements Comparable<State<T>>{
	
	private  T state;//The state represented by a string
	private double cost;//Cost to reach this state
	private State<T> cameFrom;//The state we came from to this state

	/**
	 * Constructor that get state,cost and from where the state 
	 * is come(Who is his father)
	 * @param T state , double cost,State<T> cameFrom
	 */
	//Constructor
	public State(T state , double cost,State<T> cameFrom)
	{
		this.state = state;
		this.cost = cost;
		this.cameFrom = cameFrom;
	}
	
	/**
	 * Constructor that get state
	 * @param T state
	 */
	public State(T state) {
		this.state = state;
	}
	
	/**
	 * Default constructor
	 */
	public State(){}
	
	/**
	 * Equals method
	 * @param State<T>
	 * @return boolean
	 */
	public boolean equals(State<T> s)
	{
		return state.equals(s.getState());
		
	}
	
	/**
	 * Override method equals
	 * @param Object
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass() == this.getClass())
			return state.equals(((State<T>)obj).state);
		return false;
	}
	
	
	//Getters & Setters
	/**
	 * Get state
	 * @return T
	 */
	public T getState() {
		return state;
	}
	/**
	 * Set state
	 * @param T
	 */
	public void setState(T state) {
		this.state = state;
	}
	/**
	 * Get cost
	 * @return double
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * Set cost
	 * @return T
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Get getFrom(father)
	 * @return State<T>
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}
	/**
	 * Set cameFrom
	 * @param State<T>
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	 
	/**
	 * Override method to string
	 * @return String in this structure (x,y,z)
	 */
	@Override
	public String toString()
	{
		return state.toString();
	}

	/**
	 * Override method comperTo
	 * @return int 
	 */
	@Override
	public int compareTo(State<T> s) {
		if(this.getCost()>s.getCost())
			return 1;
		if(this.getCost()<s.getCost())
			return -1;
		return 0;
	}
	
	/**
	 * Override method hashCode
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return state.hashCode();
		//return this.getState().hashCode();
	}
}
