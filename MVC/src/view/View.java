package view;

public interface View {

	void start();
	void printString(String s);
	void printMaze3d(int[][][] arr,String name);
	void crossSectionPrint(int[][] arr,char sectionType,String name,int section);
	void saveMazeInFile(byte[] byteArray, String name, String fileName);
	
}
