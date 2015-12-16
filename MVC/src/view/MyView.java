package view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import controller.Controller;
import io.MyCompressorOutputStream;

public class MyView implements View {

	Controller c;
	CLI cli;
	
	public MyView(Controller c,CLI cli) {
		this.c = c;
		this.cli = cli;
	}
	
	@Override
	public void start() {
		cli.start();
	}

	@Override
	public void printString(String s) {
		//System.out.println(s);
		cli.getOut().println(s);
		cli.getOut().flush();
	}

	@Override
	public void printMaze3d(int[][][] arr,String name) {
		//System.out.println("Maze name: "+name);
		cli.getOut().println("Maze name: "+name);
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				for(int k=0;k<arr[0][0].length;k++)
				{
					//System.out.print(arr[i][j][k] + " ");
					cli.getOut().print(arr[i][j][k] + " ");
				}
				//System.out.println();
				cli.getOut().println();
			}
			//System.out.println();
			cli.getOut().println();
		}
		cli.getOut().flush();
	}

	@Override
	public void crossSectionPrint(int[][] arr, char sectionType, String name,int section) {
		//System.out.println("Maze name: "+name);
		cli.getOut().println("Maze name: "+name);
		//System.out.println("Section by "+sectionType+" = "+section);
		cli.getOut().println("Section by "+sectionType+" = "+section);
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				//System.out.print(arr[i][j] + " ");
				cli.getOut().print(arr[i][j] + " ");
			}
			//System.out.println();
			cli.getOut().println();
		}
		cli.getOut().flush();
	}

	@Override
	public void saveMazeInFile(byte[] byteArray, String name, String fileName) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				MyCompressorOutputStream outFile;
				try {
					outFile = new MyCompressorOutputStream(new FileOutputStream(fileName));
					outFile.write(byteArray);
					String s = "file "+fileName+" is ready";
					printString(s);
					outFile.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		},"save maze view thread").start();
	}

}



