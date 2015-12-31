package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable{

	Display display;
	Shell shell;
	
	public BasicWindow(String title,int x,int y) {
		display = new Display();
		shell = new Shell();
		shell.setText(title);
		shell.setSize(x,y);
	}
	
	abstract void initWidgets();
	
	public void run()
	{
		initWidgets();
		shell.open();
		
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
				display.sleep();
		}
		
		display.dispose();
	}
	
}
