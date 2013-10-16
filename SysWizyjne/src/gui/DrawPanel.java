package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DrawPanel extends JScrollPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5596101897343033330L;
	private BufferedImage _image;

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

	public void draw(BufferedImage image) 
	{
		_image=image;
		repaint();
	}
	
    @Override
    public void paintComponent(Graphics g)
    {
           Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(_image, 0,0, this);
 
     }

}
