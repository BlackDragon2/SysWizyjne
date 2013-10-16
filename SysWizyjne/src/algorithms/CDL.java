package algorithms;

import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.File;

public class CDL extends Algorithm 
{

	@Override
	public int[][] transform(File[] files) 
	{
		int[][] epi=new int[files.length][];
		BufferedImage image=null;
		for(int i=0; i<files.length;i++)
		{
			image=GraphicIO.getImage(files[i]);
			epi[i]=new int[image.getWidth()];
			for(int x=0; x<image.getWidth();x++)
			{
				epi[i][x]=image.getRGB(x, 500);
			}
			System.out.println("image "+i+" done");
		}
		return epi;		
	}

}
