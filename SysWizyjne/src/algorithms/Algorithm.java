package algorithms;

import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Algorithm 
{

	public BufferedImage compute(File[] files) 
	{
		int[][] img=transform(GraphicIO.getImagesInArray(files));
		BufferedImage image=new BufferedImage(img.length,img[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<img.length;x++)
			for(int y=0;y<img[0].length;y++)
				image.setRGB(x, y, img[x][y]);
		return image;
	}
	
	public abstract int[][] transform(int[][][] images);

}
