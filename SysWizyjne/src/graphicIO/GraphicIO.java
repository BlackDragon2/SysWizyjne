package graphicIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utils.GraphicUtilities;
import enums.GreyMethod;
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
	 * Method saving image on disc as indicated file
	 * @param img Matrix of RBG values representing an image.
	 * @param file Path under which image should be saved.
	 */
	public static void saveImage(int[][] img, File file)
	{
		saveImage(createImage(img), file);
	}
	
	/**
	 * Method saving image on disc as indicated file
	 * @param img Matrix of RBG values representing an image.
	 * @param file Path as String under which image should be saved.
	 */
	public static void saveImage(int[][] img, String file)
	{
		saveImage(createImage(img), new File(file));
	}
	
	/**
	 * Method saving image on disc as indicated file
	 * @param image Image to be saved.
	 * @param file Path as String under which image should be saved.
	 */
	public static void saveImage(BufferedImage image, String file)
	{
		saveImage(image, new File(file));
	}
	
	/**
	 * Method creating an image from an two-dimensional array of RGB values.
	 * @param points Two-dimensional array of RGB values.
	 * @return Image corresponding to the array.
	 */
	public static BufferedImage createImage(int[][] points)
	{
		return createImage(points, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Method creating an image from an two-dimensional array of defined color models values.
	 * @param points Two-dimensional array of RGB values.
	 * @param type BufferedImage type.
	 * @return Image corresponding to the array.
	 */
	public static BufferedImage createImage(int[][] points, int type)
	{
		BufferedImage image=new BufferedImage(points.length, points[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0; x<points.length; x++)
			for(int y=0; y<points[0].length;y++)
			{
				if(type!=BufferedImage.TYPE_BYTE_GRAY)
					image.setRGB(x, y, points[x][y]);
				else image.setRGB(x, y, RGB(points[x][y]));
			}
		return image;		
	}
	
	/**
	 * Supporting method transforming grayscale value to RBG to be later saved.
	 * @param value Value of color in greyscale
	 * @return RBG color as r=b=g=value
	 */
	private static int RGB(int value) 
	{
		int result = 0;
		result+= (value<< 16);
		result+= (value<< 8);
		result+=  value;
		return result;
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
					image.setRGB(y, i, points[y][i]);
		}
		return image;		
	}
	
	/**
	 * Method creating a greyscale image from an two-dimensional array of RGB values.
	 * @param points Two-dimensional array of RGB values.
	 * @return Image corresponding to the array.
	 */
	public static BufferedImage createGreyscaleImage(int[][] points, GreyMethod method)
	{				
		return createImage(GraphicUtilities.toGreyScale(points, method), BufferedImage.TYPE_BYTE_GRAY);		
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
	
	/**
	 * Method transforming an image to two-dimensional array.
	 * @param path String path to the file.
	 * @return Two-dimensional array of RBG representing the image.
	 */
	public static int[][] getImageInArray(String path) 
	{
		return getImageInArray(new File(path));
	}
	
	/**
	 * Scales input image to given size (width and height).
	 * @param inputImage - image to be scaled.
	 * @param outputWidth - required length of image.
	 * @param outputHeight - required height of image.
	 * @return Scaled image.
	 */
	public static BufferedImage scaleImage(BufferedImage inputImage, int outputWidth, int outputHeight)
	{
		BufferedImage resizedImage = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(inputImage, 0, 0, outputWidth, outputHeight, null);
		g.dispose();
		return resizedImage;
	}
}