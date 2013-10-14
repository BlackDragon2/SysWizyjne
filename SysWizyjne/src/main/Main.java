package main;

import org.w3c.dom.DOMException;

import gui.MainFrame;

public class Main {

	public static void main(String[] args) 
	{
		MainFrame frame=new MainFrame();
		try {
			frame.init();
		} catch (DOMException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
