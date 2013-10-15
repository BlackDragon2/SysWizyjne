package graphicIO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class graphicIO 
{
	public static Image createImage(int[][] points)
	{
		BufferedImage image=new BufferedImage(points.length, points[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0; x<points.length; x++)
			for(int y=0; y<points[0].length;y++)
				image.setRGB(x, y, points[x][y]);
		return image;
		
	}
	
	public static Image getImage(File file)
	{
		BufferedImage image = null;
		try 
		{
		    image = ImageIO.read(file);
		} 
		catch (IOException e) 
		{
		}
		return image;
		
	}
	
	//obraz rgb zapisany w tablicy kolejno x, y, wartoœæ rgb
	public static int[][] getImageInArray(File file)
	{
		BufferedImage image = null;
		int[][] img=null;
		try 
		{
		    image = ImageIO.read(file);
		    img=new int[image.getWidth()][image.getHeight()];
		    for(int x=0;x<image.getWidth();x++)
		    	for(int y=0;y<image.getHeight();y++)
		    		img[x][y]=image.getRGB(x, y);
		} 
		catch (IOException e) 
		{
		}
		return img;		
	}
	
	public static Image[] getImages(File[] files)
	{
		return null;
		
	}
	
	//obrazy rgb zapisane w tablicy kolejno nr obrazu, x, y, wartoœæ rgb
	public static int[][][] getImagesInArray(File[] files)
	{
		return null;
		
	}
}
