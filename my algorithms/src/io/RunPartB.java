package io;
/*
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
*/
public class RunPartB {
		
		/*public static void main(String[] args) { 
			MyMaze3dGenerator m = new MyMaze3dGenerator(10, 1, 3);
			// save it to a file   
			OutputStream out = null;
			try {
				out = new MyCompressorOutputStream( new FileOutputStream("1.maz"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			try {
				out.write(m.getMaze().toByteArray());  
				out.flush();   
				out.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			InputStream in = null;
			try {
				in = new MyDecompressorInputStream( new FileInputStream("1.maz"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}   
			byte b[]=new byte[m.getMaze().toByteArray().length];   
			try {
				in.read(b);
				in.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			Maze3d loaded=new Maze3d(b);     
			System.out.println(loaded.equals(m.getMaze())); 
		}*/
}
