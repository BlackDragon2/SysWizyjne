package algorithms;

import enums.Position;
import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class for depth labeling algorithms using light fields
 * @author Bartek
 * @version 1.0
 */
public abstract class Algorithm 
{

	public void compute(File directory, Position position) 
	{
		if(!EPIExists(directory, position))
			createEPI(directory, position);
		int[][] depthMap=transform(directory, position);
	}
	
	/**
	 * Method creates epipolar plane images along given direction (e.g. horizontal) and saves them as plain txt
	 * under specified directories.
	 * Source images should be placed in folders with name corresponding to position inside passed directory
	 * e.g. C:\...\images\Vertical.
	 * Each EPI file has the same base name as directory, constant camera line s* or t* and position along which it was made,
	 * all values separated by "_". Inside each EPI first two lines are the width and height of matrix (image) seperated by new line sign,
	 * bellow there are RGB colors saved as integers values in row, single values are separated by comma.
	 * @param directory Directory in which EPI's should be saved. Also under this directory all source images should be saved in folders
	 * with names corresponding to choosen position
	 * @param position Name of axis along which EPI's should be created i.e. HORIZONTAL, VERTICAL or BOTH
	 */
	public static void createEPI(File directory, Position position)
	{
			File[] files;
			BufferedImage image;
		
			int width;
			int height;
			int filesNR;
			File[] writeFiles;
			BufferedWriter writer = null;
		
			String name=directory.getName();
			
			//removing "_" from directory name as it's used as a separator in EPIs names
			name=name.replace("_", "");
			if((position==Position.HORIZONTAL||position==Position.BOTH)&&!EPIExists(directory, Position.HORIZONTAL))
			{
				System.out.println("Writing Horizontal data");
				
				File file=new File(directory, Position.HORIZONTAL.toString());
				files=file.listFiles();
				
				//getting sample image size
				image=GraphicIO.getImage(files[0]);
				height=image.getHeight();
				width=image.getWidth();
				
				filesNR=files.length;
				//target files
				writeFiles=new File[height];
				
				//creating files
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
				
				//save size of matrix/image
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
				
				//saving RBG going from one image to another, saving all rows in corresponding files
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
						}
						catch(IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					}
				}	
				System.out.println("Writing data done");
			}
			if((position==Position.VERTICAL||position==Position.BOTH)&&!EPIExists(directory, Position.VERTICAL))
			{
				System.out.println("Writing Vertical data");
				
				File file=new File(directory, Position.VERTICAL.toString());
				files=file.listFiles();
				
				//getting sample image size
				image=GraphicIO.getImage(files[0]);
				height=image.getHeight();
				width=image.getWidth();
				filesNR=files.length;
				
				//target files
				writeFiles=new File[width];
				
				//creating files
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
				
				//save size of matrix/image
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
				
				//saving RBG going from one image to another, saving all columns in corresponding files
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
						}
						catch(IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println("Writing data done");
			}			
	}

	/**
	 * Method checks whether EPI are already saved or not
	 * @param directory Directory in which EPI's should be saved. Also under this directory all source images should be saved in folders
	 * with names corresponding to choosen position
	 * @param position Name of axis along which EPI's should exist i.e. HORIZONTAL, VERTICAL or BOTH
	 * @return true if all EPIs exist false otherwise
	 */
	public static boolean EPIExists(File directory, Position position) 
	{
		//flag holding the result of the method
		boolean result=true;
		
		BufferedImage image;
		File file;
		File[] files;
		if(position==Position.HORIZONTAL||position==Position.BOTH)
		{
			file=new File(directory, Position.HORIZONTAL.toString());
			files=file.listFiles();
			
			//getting size of samle image
			image=GraphicIO.getImage(files[0]);
			int height=image.getHeight();
			
			String name=directory.getName();
			//removing "_" from directory name as it's used as a separator in EPIs names
			name=name.replace("_", "");
			for(int y=0;y<height&&result;y++)
			{
				file=new File(directory, name+"_"+y+"_"+Position.HORIZONTAL.toString()+".txt");
				result=result&&file.exists();
			}				
		}
		if(position==Position.VERTICAL||position==Position.BOTH)
		{
			file=new File(directory, Position.VERTICAL.toString());
			files=file.listFiles();
			
			//getting size of sample image
			image=GraphicIO.getImage(files[0]);
			int width=image.getWidth();
			
			String name=directory.getName();
			//removing "_" from directory name as it's used as a separator in EPIs names
			name=name.replace("_", "");
			for(int x=0;x<width&&result;x++)
			{
				file=new File(directory, name+"_"+x+"_"+position.toString()+".txt");
				result=result&&file.exists();
			}	
		}		
		return result;
	}

	/**
	 * Abstract method which should be implemented by every algorithm, called from compute. It should return depth map as int[][] as a result.
	 * @param directory Directory where EPIs can be found.
	 * @param position Axis around which EPIs are calculated (HORIZONTAL, VERTICAL or BOTH)
	 * @return Depth map corresponding to input images, as an array of point (x,y) and their corresponding color estimating the depth.
	 */
	public abstract int[][] transform(File directory, Position position);

}
