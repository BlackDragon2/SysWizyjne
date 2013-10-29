package graphicIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import enums.Position;

/**
 * Class supporting loading and saving image/images. All methods are static.
 * @author Bartek
 * @version 1.0
 */
public class GraphicIO 
{
	
	/**
	 * Method saving image on disc as indicated file
	 * @param image Image to be saved.
	 * @param file Path under which image should be saved.
	 */
	public static void saveImage(BufferedImage image, File file)
	{
		try 
		{
			file.createNewFile();
			ImageIO.write(image, "jpg", file);
		} 
		catch (IOException e1) 
		{
			System.out.println("Image saving failed");
			e1.printStackTrace();			
		}
	}
	
	/**
	 * Method creating an image from an two-dimensional array of RGB values.
	 * @param points Two-dimensional array of RGB values.
	 * @return Image corresponding to the array.
	 */
	public static BufferedImage createImage(int[][] points)
	{
		BufferedImage image=new BufferedImage(points.length, points[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0; x<points.length; x++)
			for(int y=0; y<points[0].length;y++)
				image.setRGB(x, y, points[x][y]);
		return image;		
	}
	
	/**
	 * Method creating an EPI-image from an two-dimensional array of RGB values.
	 * @param points Two-dimensional array of RGB values.
	 * @param position orientation of EPI
	 * @return Image corresponding to the array.
	 */
	public static BufferedImage createImage(int[][] points, Position position)
	{
		BufferedImage image;
		if(position==Position.HORIZONTAL)
		{
			image=new BufferedImage(points[0].length, points.length, BufferedImage.TYPE_INT_RGB);
			for(int i=0; i<points.length; i++)
				for(int x=0; x<points[0].length;x++)
					image.setRGB(x, i, points[i][x]);
		}
		else
		{
			image=new BufferedImage(points.length, points[0].length, BufferedImage.TYPE_INT_RGB);
			for(int y=0; y<points.length; y++)
				for(int i=0; i<points[0].length;i++)
					image.setRGB(i, y, points[i][y]);
		}
		return image;		
	}
	
	/**
	 * Method creating a greyscale image from an two-dimensional array of RGB values.
	 * @param points Two-dimensional array of RGB values.
	 * @return Image corresponding to the array.
	 */
	public static BufferedImage createGreyscaleImage(int[][] points)
	{
		BufferedImage image=new BufferedImage(points.length, points[0].length, BufferedImage.TYPE_BYTE_GRAY);
		for(int x=0; x<points.length; x++)
			for(int y=0; y<points[0].length;y++)
				image.setRGB(x, y, points[x][y]);
		return image;
		
	}
	
	/**
	 * Method loading image from the disc.
	 * @param file File to be loaded.
	 * @return Loaded image.
	 */
	public static BufferedImage getImage(File file)
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
	
	/**
	 * Method transforming an image to two-dimensional array.
	 * @param file Image to be transformed.
	 * @return Two-dimensional array of RBG representing the image.
	 */
	public static int[][] getImageInArray(File file)
	{
		int[][] img=null;
		BufferedImage image = getImage(file);
		img=new int[image.getWidth()][image.getHeight()];
		for(int x=0;x<image.getWidth();x++)
			for(int y=0;y<image.getHeight();y++)
				img[x][y]=image.getRGB(x, y);
		return img;		
	}	
}