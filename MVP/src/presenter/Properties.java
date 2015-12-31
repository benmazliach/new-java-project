package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xSize;
	private int ySize;
	private int zSize;
	private String algorithmSearchName;
	private int numOfThreads;
	private String algorithmGenerateName;
	private String userInterface;
	private String mazeName;
	
	
	public int getxSize() {
		return xSize;
	}


	public void setxSize(int xSize) {
		this.xSize = xSize;
	}


	public int getySize() {
		return ySize;
	}


	public void setySize(int ySize) {
		this.ySize = ySize;
	}


	public int getzSize() {
		return zSize;
	}


	public void setzSize(int zSize) {
		this.zSize = zSize;
	}


	public String getAlgorithmSearchName() {
		return algorithmSearchName;
	}


	public void setAlgorithmSearchName(String algorithmSearchName) {
		this.algorithmSearchName = algorithmSearchName;
	}


	public int getNumOfThreads() {
		return numOfThreads;
	}


	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}


	public String getAlgorithmGenerateName() {
		return algorithmGenerateName;
	}


	public void setAlgorithmGenerateName(String algorithmGenerateName) {
		this.algorithmGenerateName = algorithmGenerateName;
	}


	public Properties() {
		this.xSize=0;
		this.ySize=0;
		this.zSize=0;
		this.algorithmSearchName=null;
		this.numOfThreads=0;
		this.algorithmGenerateName=null;
		this.userInterface=null;
		this.mazeName = null;
	}


	public String getUserInterface() {
		return userInterface;
	}


	public void setUserInterface(String userInterface) {
		this.userInterface = userInterface;
	}


	public String getMazeName() {
		return mazeName;
	}


	public void setMazeName(String mazeName) {
		this.mazeName = mazeName;
	}
	
}
