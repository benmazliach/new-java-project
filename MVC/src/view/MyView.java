package view;

import controller.Controller;

public class MyView implements View {

	Controller c;
	CLI cli;
	
	public MyView(Controller c,CLI cli) {
		this.c = c;
		this.cli = cli;
	}
	
	@Override
	public void start() {
		cli.start();
	}

	@Override
	public void printString(String s) {
		System.out.println(s);
	}

}
