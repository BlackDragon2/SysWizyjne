package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Panel on which result images should be displayed.
 * @author Bartek
 * @version 1.0
 */
public class ImagePanel extends JPanel 
{
	private static final long serialVersionUID = 3006769532505931833L;
	private BufferedImage _image;
 
    public void setImage(BufferedImage image)
    {
    	_image=image;
    }
 
    @Override
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(_image, 0,0, this);
 
    }
}