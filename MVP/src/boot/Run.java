package boot;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
import model.MyModel;
import presenter.Command;
import presenter.MyPresenter;
import view.CLI;
import view.MyView;

public class Run {
		
	public static void main(String[] args) { 
		HashMap<String, Command> c = new HashMap<String, Command>();
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = null;
		out = new PrintWriter(new OutputStreamWriter(System.out));
		
		
		MyModel m = new MyModel();
		CLI cli = new CLI(in,out, c);
		MyView v = new MyView(cli);
		cli.setView(v);
		MyPresenter p = new MyPresenter(c,m,v);
		
		// out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("ben.txt")));
			//	out.write("generate 3d maze ben 5 5 5 MyMaze3dGenerator\n");
				//out.write("dir src\n");
			//	out.write("display ben\n");
			//	out.write("display cross section by Y 2 for ben\n");
			//	out.write("save maze ben fileMaze\n");
			//	out.write("load maze fileMaze ben\n");
			//	out.write("maze size ben\n");
			//	out.write("file size file\n");
			//	out.write("solve ben BFS\n");
			//	out.write("display solution ben\n");
			//	out.write("exit\n");
		 
		m.addObserver(p);
		v.addObserver(p);
		v.start();
	}
}



