package graphicIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicIO 
{
	
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
	
	public static BufferedImage createImage(int[][] points)
	{
		BufferedImage image=new BufferedImage(points.length, points[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0; x<points.length; x++)
			for(int y=0; y<points[0].length;y++)
				image.setRGB(x, y, points[x][y]);
		return image;
		
	}
	
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
	
	//obraz rgb zapisany w tablicy kolejno x, y, wartoœæ rgb
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