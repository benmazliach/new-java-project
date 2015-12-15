package algorithms.mazeGenerators;

/**
*<h1> Position class <h1>
* This class present position in the maze
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/

public class Position {
	
	private int pX;
	private int pY;
	private int pZ;
	
	/**
	 * Constructor that creates Position
	 * @param int x , int y , int z the Position x,y,z
	 */
	public Position(int x,int y,int z)
	{
		this.pX = x;
		this.pY = y;
		this.pZ = z;
	}
	
	/**
	 * Get x
	 * @return int
	 */
	public int getpX() {
		return pX;
	}
	/**
	 * Set x
	 * @param int
	 */
	public void setpX(int pX) {
		this.pX = pX;
	}
	/**
	 * Get y
	 * @return int
	 */
	public int getpY() {
		return pY;
	}
	/**
	 * Set y
	 * @param int
	 */
	public void setpY(int pY) {
		this.pY = pY;
	}
	/**
	 * Get z
	 * @return int
	 */
	public int getpZ() {
		return pZ;
	}
	/**
	 * Set z
	 * @param int
	 */
	public void setpZ(int pZ) {
		this.pZ = pZ;
	}
	
	/**
	 * Override method to string
	 * @return String in this structure (x,y,z)
	 */
	@Override
	public String toString()
	{
		return "{"+this.getpX()+","+this.getpY()+","+this.getpZ()+"}";
	}
	
	/**
	 * Method like equals
	 * @param Position
	 * @return boolean
	 */
	public boolean same(Position p)
	{
		if(this.getpX()==p.getpX()&&this.getpY()==p.getpY()&&this.getpZ()==p.getpZ())
			return true;
		return false;
	}
	
	/**
	 * Equals method
	 * @param Position
	 * @return boolean
	 */
	public boolean equals(Position pos)
	{
		if(this.getpX()==pos.getpX()&&this.getpY()==pos.getpY()&&this.getpZ()==pos.getpZ())
			return true;
		return false;
	}
	
	/**
	 * Override method equals
	 * @param Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass() == this.getClass())
			return equals((Position)obj);
		return false;
	}
	
	/**
	 * Override method hashCode
	 * @return int
	 */
	@Override
	public int hashCode() {
		String s = "" + this.getpX() + this.getpY() + this.getpZ();
		return s.hashCode();
	}
}
