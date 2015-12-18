package boot;

import java.io.BufferedReader;
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
			
			PrintWriter out = null;
			//Example of commands
			/*try {
				out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("ben.txt")));
				out.write("generate 3d maze ben 5 5 5 MyMaze3dGenerator\n");
				out.write("dir src\n");
				out.write("display ben\n");
				out.write("display cross section by Y 2 for ben\n");
				out.write("save maze ben file\n");
				out.write("load maze file ben\n");
				out.write("maze size ben\n");
				out.write("file size file\n");
				out.write("solve ben BFS\n");
				out.write("display solution ben\n");
				out.write("exit\n");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			out.close();*/
			
			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(System.in));
			/*try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream("ben.txt")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			out = new PrintWriter(new OutputStreamWriter(System.out));
			/*try {
				out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("test.txt")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}*/
			MyController con = new MyController(c);
			CLI d = new CLI(in, out, c);
			MyModel m = new MyModel(con);
			
			MyView v = new MyView(con, d);
			con.setModel(m);
			con.setView(v);
			v.start();
			
		}
}
