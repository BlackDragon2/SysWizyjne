package algorithms;

import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.File;

public class CDL extends Algorithm 
{

	@Override
	public int[][] transform(File[] files) 
	{		
		BufferedImage image=GraphicIO.getImage(files[0]);
		int[][][] epi=new int[image.getHeight()][image.getWidth()][files.length];
		for(int y=0; y<image.getHeight();y++)
		{
			for(int x=0; x<image.getWidth(); x++)
				epi[y][x][0]=image.getRGB(x, y);
		}
		for(int i=1; i<files.length;i++)
		{
			image=GraphicIO.getImage(files[i]);
			for(int y=0; y<image.getHeight();y++)
			{
				for(int x=0; x<image.getWidth(); x++)
					epi[y][x][i]=image.getRGB(x, y);
			}
			System.out.println("image "+i+" done");
		}
		return epi[0];		
	}

}
