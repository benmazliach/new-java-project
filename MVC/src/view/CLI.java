package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

/**
 * <h1>  CLI Class <h1>
 * This class manage the Command Line Interface for the client
 * 
 * @author  Ben Mazliach & Or Moshe
 * @version 1.0
 * @since   17/12/15
 */
public class CLI implements Runnable{
	
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> hashMap;
	
	/**
	 * Constructor - initialize CLI
	 * @param in,out,hashMap BufferedReader ,PrintWriter,HashMap<String, Command>
	 */
	public CLI(BufferedReader in,PrintWriter out,HashMap<String, Command> hashMap)
	{
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
		this.hashMap = hashMap;
	}

	/**
	 * Type of Runnable so we override run function
	 */
	@Override
	public void run() {
		this.start();
	}
	
	/**
	 * Start the thread manage all commands
	 */

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
						//If the input come from file to generate and solve will take time to set solution
						/*if(hashMap.containsKey("generate maze 3d")==true || hashMap.containsKey("solve")==true )
						{
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}*/
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				hashMap.get(s).doCommand(args);
			}
		},"cli thread").start();
	}
	
	/**
	 * Get in
	 * @return BufferedReader
	 */
	public BufferedReader getIn() {
		return in;
	}
	/**
	 * Set in
	 * @param BufferedReader
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	/**
	 * Get out
	 * @return PrintWriter
	 */
	public PrintWriter getOut() {
		return out;
	}
	/**
	 * Set out
	 * @param PrintWriter
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	/**
	 * Get hashMap
	 * @return HashMap<String, Command>
	 */
	public HashMap<String, Command> getHashMap() {
		return hashMap;
	}
	/**
	 * Set hashMap
	 * @param HashMap<String, Command>
	 */
	public void setHashMap(HashMap<String, Command> hashMap) {
		this.hashMap = hashMap;
	}

}
