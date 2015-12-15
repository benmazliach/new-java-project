package io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
*<h1>  MyCompressorOutputStream class <h1>
*This class extends OutputStream
*This class get type of OutputStream that we want to compress to him
* 
*
* @author  Ben Mazliach , Or Moshe
* @version 1.0
* @since   17/12/15
*/

public class MyCompressorOutputStream extends OutputStream{

	private OutputStream out;
	private Integer count;
	
	/**
	 * Constructor that get OutputStream
	 * @param OutputStream out
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
		this.count = new Integer(0);
	}
	
	/**
	 * Writes b.length bytes from the specified byte array to this output stream.
	 * @param byte[] b
	 * @throws IOException
	 */
	@Override
	public void write(byte[] b) throws IOException
	{
		this.count = 0;
		Integer temp = -1;
		if(b.length==1)
		{
			this.write(b[0]);
		}
		else
		{
			for(int i=0;i<b.length;i++)
			{
				if(temp!=b[i])
				{
					if(temp!=-1)
					{
						this.write(temp.byteValue());
						out.write(",".getBytes());
					}
					temp=(int) b[i];
					count=1;
				}
				else
				{
					count++;
				}
				if(i+1==b.length)
					this.write(temp.byteValue());
			}
			this.count = 1;
		}
	}
	
	/**
	 * Writes the specified byte to this output stream.
	 * @param int b
	 * @throws IOException
	 */
	@Override
	public void write(int b) throws IOException {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		//if b is bigger than 9 we need to write all digits
		while(b>=10)
		{
			int g = b%10;
			temp.add(g);
			b/=10;
		}
		out.write(b+48);
		for(int i = temp.size()-1;i>=0;i--)
			out.write(temp.get(i)+48);
		out.write(",".getBytes());
		out.write(count.toString().getBytes());
	}

	/**
	 * Get out
	 * @return OutputStream
	 */
	//getter & setter maze
	public OutputStream getOut() {
		return out;
	}
	/**
	 * Set out 
	 * @param OutputStream out
	 * @return void 
	 */
	public void setOut(OutputStream out) {
		this.out = out;
	}
	/**
	 * Get count 
	 * @return int
	 */
	public int getCount() {
		return count;
	}
	/**
	 * Set count 
	 * @param int count
	 * @return void 
	 */
	public void setCount(int count) {
		this.count = count;
	}

}
