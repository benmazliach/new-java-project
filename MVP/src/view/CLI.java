package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CLI implements Runnable{
	
	private BufferedReader in;
	private PrintWriter out;
	private View view;
	
	public CLI(BufferedReader in,PrintWriter out)
	{
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
	}

	@Override
	public void run() {
		this.start();
	}

	public void start()
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				String[] args = null;
				String s = null;
				try {
					while((s=in.readLine()).equals("exit")!=true)
					{
						args = s.split(" ");
						view.setCommand(args);
					}
				} catch (IOException e) {
					view.printString(e.getMessage());
				}
				view.setCommand(args = s.split(" "));
			}
		},"cli thread").start();
	}
	
	public BufferedReader getIn() {
		return in;
	}
	
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
	public PrintWriter getOut() {
		return out;
	}
	
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

}
