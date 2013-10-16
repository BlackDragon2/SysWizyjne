package gui;

import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;

public class DrawPanel extends JScrollPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5596101897343033330L;
	private ImagePanel _panel;

	public DrawPanel() 
	{
		super();
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void init() 
	{
		_panel=new ImagePanel();
		setViewportView(_panel);	
	}

	public void draw(BufferedImage image) 
	{
		_panel.setImage(image);
		_panel.repaint();
		repaint();
	}
}
