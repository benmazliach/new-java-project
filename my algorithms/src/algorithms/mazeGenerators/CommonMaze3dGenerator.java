package algorithms.mazeGenerators;

/**
*<h1> CommonMaze3dGenerator  class <h1>
* This class present general class 
* Its definer 2 method that one abstract and one 
* that common to any class that extends from this class
*
* @author  Ben Mazliach
* @version 1.0
* @since   29/11/15
*/

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	//Maze3d
	protected Maze3d maze;
	
	/**
	 * Generate method that creates a maze
	 * @param int x ,int y ,int z the sizes of the maze
	 * @return Maze3d
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	public abstract Maze3d generate(int x,int y,int z);
	
	/**
	 * measureAlgorithmTime method that calculates the time that
	 * generate is working
	 * @param int x ,int y ,int z the sizes of the maze
	 * @return String
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	public String measureAlgorithmTime(int x,int y,int z)
	{
		Long t;
		t = System.currentTimeMillis();
		generate(x, y, z);
		t = System.currentTimeMillis() - t;
		return t.toString();
	}
	
	/**
	 * Return the value of this position
	 * @param int x ,int y ,int z 
	 * @return int
	 */
	public int getValuePos(int x,int y,int z)
	{
		return maze.returnValue(x, y, z);
	}
	
	/**
	 * Get maze size
	 * @return int
	 */
	//size getters 
	public int getXs()
	{
		return maze.getXSize();
	}
	public int getYs()
	{
		return maze.getYSize();
	}
	public int getZs()
	{
		return maze.getZSize();
	}
	
	
	/**
	 * Return start & goal position
	 * @return Position
	 */
	public Position getStartPos()
	{
		return maze.getStartPosition();
	}
	public Position getGoalPos()
	{
		return maze.getGoalPosition();
	}
	
	/**
	 * Get all possible moves from position
	 * @param Position
	 * @return String
	 */
	public Position[] getPossibleMovesPosition(Position p)
	{
		return maze.getPossiblePositions(p);
	}
	
	/**
	 * Return the maze
	 * @return Maze3d
	 */
	public Maze3d getMaze()
	{
		return maze;
	}

}
