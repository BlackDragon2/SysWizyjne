package algorithms;

import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Algorithm 
{

	public BufferedImage compute(File[] files) 
	{
		int[][] img=transform(files);
		BufferedImage image=new BufferedImage(img.length, img[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0; x<img.length; x++)
			for(int y=0; y<img[x].length; y++)
				image.setRGB(x, y, img[x][y]);
		return image;
	}
	
	public void createEPI(File directory, Position position)
	{
		createEPI(directory.listFiles(), position);
	}
	
	public void createEPI(File[] files, Position position)
	{
		File directory=files[0].getParentFile();
		BufferedImage image=GraphicIO.getImage(files[0]);
		int width=image.getWidth();
		int height=image.getHeight();
		int filesNR=files.length;
		File[] writeFiles;
		BufferedWriter writer = null;
		
		String name=directory.getName();
		name=name.replace("_", "");
		if(position==Position.HORIZONTAL)
		{
			writeFiles=new File[height];
			for(int y=0;y<height;y++)
			{
				writeFiles[y]=new File(directory, name+"_"+y+"_"+position.toString()+".txt");
				try 
				{
					writeFiles[y].createNewFile();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			writeFiles=new File[width];
			for(int x=0;x<width;x++)
			{
				writeFiles[x]=new File(directory, name+"_"+x+"_"+position.toString()+".txt");
				try 
				{
					writeFiles[x].createNewFile();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Files created, writing data started");
		
		if(position==Position.HORIZONTAL)
		{
			for(int y=0;y<height;y++)
			{
				
				try 
				{
					writer=new BufferedWriter(new FileWriter(writeFiles[y], true));
					writer.write(String.valueOf(width));
					writer.newLine();
					writer.write(String.valueOf(filesNR));
					writer.newLine();
					writer.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			for(int i=0;i<filesNR;i++)
			{
				image=GraphicIO.getImage(files[i]);

				for(int y=0;y<height;y++)
				{
					try
					{
						writer=new BufferedWriter(new FileWriter(writeFiles[y], true));
						for(int x=0;x<width;x++)
						{
							writer.write(String.valueOf(image.getRGB(x, y)));
							writer.write(",");
						}
						writer.newLine();
						writer.close();
					}catch(IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}
		else
		{
			for(int x=0;x<height;x++)
			{

				try 
				{
					writer=new BufferedWriter(new FileWriter(writeFiles[x], true));
					writer.write(String.valueOf(height));
					writer.newLine();
					writer.write(String.valueOf(filesNR));
					writer.newLine();
					writer.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			for(int i=0;i<filesNR;i++)
			{
				image=GraphicIO.getImage(files[i]);
				
				for(int x=0;x<width;x++)
				{		
					try
					{
						writer=new BufferedWriter(new FileWriter(writeFiles[x], true));
						for(int y=0;y<height;y++)
						{
							writer.write(String.valueOf(image.getRGB(x, y)));
							writer.write(",");
						}
						writer.newLine();
						writer.close();
					}catch(IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("Writing data done");
	}

	
	public abstract int[][] transform(File[] files);

}
