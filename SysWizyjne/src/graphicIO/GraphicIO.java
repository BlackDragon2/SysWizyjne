package graphicIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicIO 
{
	
	public static void saveImage(BufferedImage image)
	{
		 File outputfile = new File("D:\\saved.jpg");
		 try {
			ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) 
		{
			System.out.println("Image saving failed");
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
	
	public static BufferedImage[] getImages(File[] files)
	{
		BufferedImage[] images = null;
		try 
		{
			images=new BufferedImage[files.length];
			for(int i=0;i<files.length;i++)
				images[i] = ImageIO.read(files[i]);
		} 
		catch (IOException e) 
		{
		}
		catch (OutOfMemoryError f)
		{
			System.out.println("Brakuje mi stosu dziadzie");
		}
		return images;		
	}
	
	//obrazy rgb zapisane w tablicy kolejno nr obrazu, x, y, wartoœæ rgb
	public static int[][][] getImagesInArray(File[] files)
	{
		int[][][] imgs = null;
		BufferedImage[] images = getImages(files);
		imgs=new int[files.length][][];
		for(int i=0;i<files.length;i++)
		{
			imgs[i]=new int[images[i].getWidth()][images[i].getHeight()];
			for(int x=0; x<images[i].getWidth();x++)
				for(int y=0; y<images[i].getHeight(); y++)
					imgs[i][x][y]=images[i].getRGB(x, y);
		}
		return imgs;			
	}
}
