 package algorithms.mazeGenerators;
 
 /**
 *<h1> Maze3dGenerator interface <h1>
 * This interface present Each algorithm
 * that creates a maze
 *
 * @author  Ben Mazliach
 * @version 1.0
 * @since   29/11/15
 */

public interface Maze3dGenerator {
	
	/**
	 * Generate method that creates a maze
	 * @param int x ,int y ,int z
	 * @return Maze3d
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	public Maze3d generate(int x,int y,int z);
	/**
	 * measureAlgorithmTime method that calculates the time that
	 * generate is working
	 * @param int x ,int y ,int z the sizes of the maze
	 * @return String
	 */
	//maze[y=Floors][x=Lines][z=Columns]
	public String measureAlgorithmTime(int x,int y,int z);
	/**
	 * Get maze size
	 * @return int
	 */
	//size getters 
	public int getXs();
	public int getYs();
	public int getZs();
	
	/**
	 * Return the value of this position
	 * @param int x ,int y ,int z position sizes
	 * @return int
	 */
	public int getValuePos(int x,int y,int z);
	/**
	 * Return start & goal position
	 * @return Position
	 */
	public Position getStartPos();
	public Position getGoalPos();
	/**
	 * Get all possible moves from position
	 * @param Position
	 * @return String
	 */
	public Position[] getPossibleMovesPosition(Position p);
	/**
	 * Return the maze
	 * @return Maze3d
	 */
	public Maze3d getMaze();

}
