package io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
*<h1>  MyDecompressorInputStream class <h1>
*This class extends InputStream
*This class get type of InputStream that we want to decompress from him
* 
*
* @author  Ben Mazliach , Or Moshe
* @version 1.0
* @since   17/12/15
*/

public class MyDecompressorInputStream extends InputStream{

	private InputStream in;
	
	//check if we need to remove 
	//if true remove readFile
	private byte[] arrayByte;
	
	/**
	 * Constructor that get InputStream
	 * @param InputStream in
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
		this.arrayByte = null;
	}
	
	/**
	 * Reads some number of bytes from the input stream and stores them into the buffer array b.
	 * @param byte[] b
	 */
	@Override
	public int read(byte[] b)
	{
		int size=0;
		ArrayList<Byte> temp = new ArrayList<Byte>();
		try {
			int num = 0;
			int i = 0;
			while((num = this.read())!=-1)
			{
				temp.add((byte)num);
				if(i%2==1)
					size+=temp.get(i);
				i++;
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int countOfByte = 0;
		byte numByte = 0;
		int z = 0;//array b index
		for (int i = 0; i < temp.size(); i++) {
			numByte = temp.get(i);
			i++;
			countOfByte = temp.get(i);
			for(int j = 0;j<(countOfByte);j++)
			{
				b[z] = numByte;
				z++;
			}
		}
		if(b!=null || size!=0)
			return size;
		return -1;
	}
	
	/**
	 * Reads some number of bytes from the input stream and stores them into the buffer array b.
	 * @return int
	 * @throws IOException
	 */
	public int readFile() throws IOException {
		int size=0;
		ArrayList<Byte> temp = new ArrayList<Byte>();
		try {
			int num = 0;
			int i = 0;
			while((num = this.read())!=-1)
			{
				temp.add((byte)num);
				if(i%2==1)
					size+=temp.get(i);
				i++;
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		arrayByte = new byte[size];
		int countOfByte = 0;
		byte numByte = 0;
		int z = 0;//array b index
		for (int i = 0; i < temp.size(); i++) {
			numByte = temp.get(i);
			i++;
			countOfByte = temp.get(i);
			for(int j = 0;j<(countOfByte);j++)
			{
				arrayByte[z] = numByte;
				z++;
			}
		}
		if(arrayByte!=null || size!=0)
			return size;
		return -1;
	}
	
	/**
	 * Reads the next byte of data from the input stream.
	 * @return int 
	 * @throws IOException
	 */
	@Override
	public int read() throws IOException {
		int num = 0;
		int temp = in.read();
		int i=1;
		//if the number is bigger than 9 so we need to read all number still we get ','
		while(temp!=-1)
		{
			if(temp>='0' &&temp<='9')
			{
				num = num * i + (temp-48);//48('0')<=temp<=57('9')
				i = i*10;
				temp = in.read();
			}
			else
			{
				return num;
			}
		}
		if(num!=0)
			return num;
		return -1;
	}

	/**
	 * Get in
	 * @return InputStream
	 */
	public InputStream getIn() {
		return in;
	}
	/**
	 * Set in 
	 * @param InputStream in
	 * @return void 
	 */
	public void setIn(InputStream in) {
		this.in = in;
	}
	/**
	 * Get arrayByte 
	 * @return byte[]
	 */
	public byte[] getArrayByte() {
		return arrayByte;
	}
	/**
	 * Set arrayByte
	 * @param byte[] arrayByte
	 * @return void 
	 */
	public void setArrayByte(byte[] arrayByte) {
		this.arrayByte = arrayByte;
	}

}
