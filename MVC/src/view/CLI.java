package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI implements Runnable{
	
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> hashMap;
	
	public CLI(BufferedReader in,PrintWriter out,HashMap<String, Command> hashMap)
	{
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
		this.hashMap = hashMap;
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
				boolean dos = false;
				try {
					while((s=in.readLine()).equals("exit")!=true)
					{
						args = s.split(" ");
						s = null;
						dos = false;
						for(int i=0;i<args.length;i++)
						{
							if(hashMap.containsKey(s)==true && dos ==false)
							{
								dos = true;
							}
							else
							{
								if(dos==false)
								{
								if(s==null)
									s=args[i];
								else if(s!=null)
									s = s + " " +args[i];
								if((i+1)==args.length)
									s = s + " " + args[i];
								}
							}
						}
						if(hashMap.containsKey(s)==true)
						{
							hashMap.get(s).doCommand(args);
						}
						else
						{
							out.println("Error");
							out.flush();
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				//We need to check what we do with this
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//exit
				hashMap.get(s).doCommand(args);
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

	public HashMap<String, Command> getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap<String, Command> hashMap) {
		this.hashMap = hashMap;
	}

}
