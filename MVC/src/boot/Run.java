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
import controller.MyCommand;
import view.CLI;

public class Run {
		
		public static void main(String[] args) { 
		
			HashMap<String, Command> c = new HashMap<String, Command>();
			c.put("generate", new MyCommand("55"));
			c.put("dir", new MyCommand("56"));
			
			PrintWriter out = null;
			try {
				out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("ben.txt")));
				out.write("generate ben\n");
				out.write("dir ben\n");
				out.write("Print 58\n");
				out.write("exit\n");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
			BufferedReader in = null;
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
			CLI d = new CLI(in, out, c);
			Thread t = new Thread(d);
			t.start();
			
		}
}
