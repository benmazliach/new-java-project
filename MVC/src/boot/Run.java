package boot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.MyController;
import model.MyModel;
import view.CLI;
import view.MyView;

public class Run {
		
		public static void main(String[] args) { 
		
			HashMap<String, Command> c = new HashMap<String, Command>();
			//c.put("generate maze 3d", new MyCommand("55"));
			//c.put("dir", new MyCommand("56"));
			//c.put("exit", new MyCommand("56"));
			
			PrintWriter out = null;
			try {
				out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("ben.txt")));
				out.write("generate maze 3d ben 5 5 5 MyMaze3dGenerator\n");
				//out.write("dir 58\n");
				//out.write("Print 58\n");
				out.write("exit\n");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
			BufferedReader in = null;
			//in = new BufferedReader(new InputStreamReader(System.in));
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream("ben.txt")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("or.txt")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyController con = new MyController(c);
			CLI d = new CLI(in, out, c);
			MyModel m = new MyModel(con);
			
			MyView v = new MyView(con, d);
			con.setModel(m);
			con.setView(v);
			//Thread t = new Thread(d);
			//t.start();
			v.start();
			
		}
}
