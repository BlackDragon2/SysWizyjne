package gui;

import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DrawPanel extends JScrollPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5596101897343033330L;

	public DrawPanel() 
	{
		super();
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void init() 
	{
		JPanel panel=new JPanel();
		setViewportView(panel);	
	}

	public void draw(Image compute) 
	{
		
	}

}
