package algorithms;

import enums.Position;
import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class representing single EPI image created around an EPI line y* or x*
 * @author Bartek
 * @version 1.0
 */
public class EPILine 
{
	/*
	 * _position - Axis along which EPI line was taken e.g. HORIZONTAL
	 * _pixels - matrix representing the image
	 * _line - base EPI line e.g. y*=50
	 */
	private Position _position;
	private int[][] _pixels;
	private int _line;
	
	/**
	 * Constructor creating an EPILine
	 * @param pixels Matrix of RGB values representing an image
	 * @param line Base line of an EPI image e.g. y*=50
	 * @param position Axis along which EPI line was taken e.g. HORIZONTAL
	 */
	public EPILine(int[][] pixels, int line, Position position)
	{
		_position=position;
		_line=line;
		_pixels=pixels;
		
	}
	
	/**
	 * Constructor creating an EPILine
	 * @param directory Directory where source images are saved
	 * @param line Base line of an EPI image e.g. y*=50
	 * @param position Axis along which EPI line was taken e.g. HORIZONTAL
	 */
	public EPILine(File directory, int line, Position position)
	{
		String name=directory.getName();
		File file=new File(directory.getAbsolutePath(),name.replace("_", "")+"_"+line+"_"+position.toString()+".txt");
		if(file.exists())
			loadEPILine(file);
		else
			createEPILine(directory, line, position);			
	}
	
	/**
	 * Method setting EPI line's values, supposed to be used by multiple constructors.
	 * @param directory Directory where source images are saved
	 * @param line Base line of an EPI image e.g. y*=50
	 * @param position Axis along which EPI line was taken e.g. HORIZONTAL
	 */
	private void createEPILine(File directory, int line, Position position) 
	{
		_position=position;
		_line=line;
		_pixels=position==Position.HORIZONTAL? createHorizontalEPI(new File(directory,position.toString()), line):createVerticalEPI(new File(directory,position.toString()), line);
	}

	/**
	 * Method saving current object on the disc.
	 * @param directory Directory under which EPI should be saved.
	 */
	public void saveEPILine(File directory)
	{
		String name=directory.getName();
		File file=new File(directory, name.replace("_", "")+"_"+_line+"_"+_position.toString()+".txt");
		if(!file.exists())
		{
			try 
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(String.valueOf(_pixels.length));
				writer.newLine();
				writer.write(String.valueOf(_pixels[0].length));
				writer.newLine();
				for(int i=0;i<_pixels.length;i++)
					for(int j=0;j<_pixels[0].length;j++)
					{
						writer.write(String.valueOf(_pixels[i][j]));
						writer.write(",");
					}
				writer.close();						
			} catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method saving current object on the disc.
	 * @param directory String path under which EPI should be saved.
	 */
	public void saveEPILine(String directory)
	{
		saveEPILine(new File(directory));
	}
	
	
	/**
	 * Method setting object's fields from file on disc.
	 * @param file File with EPI.
	 */
	public void loadEPILine(File file)
	{
		String name=file.getName();
		int [][] result=null;
		try 
		{
			Scanner scanner=new Scanner(file);
			int size=scanner.nextInt();
			int width=scanner.nextInt();			
			result=new int[size][width];
			scanner.useDelimiter(",");
			String temp;
			for(int i=0;i<size;i++)
				for(int j=0;j<width;j++)
				{
					temp=scanner.next();
					result[i][j]=Integer.parseInt(temp.trim());
				}
			scanner.close();	
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_pixels=result;
		
		//That's why I needed to get rid of "_" in directory name
		name=name.substring(0,name.lastIndexOf("."));
		String[] split=name.split("_");
		_line=Integer.parseInt(split[1])+1;
		_position=Position.valueOf(split[2]);
		
	}


	public int[][] get_pixels() 
	{
		return _pixels;
	}


	public void set_pixels(int[][] _pixels) 
	{
		this._pixels = _pixels;
	}
	
	public int get_line() {
		return _line;
	}

	public void set_line(int _line) {
		this._line = _line;
	}

	/**
	 * EPI line with x*,s*
	 * @param directory Directory with source images
	 * @param x Const EPI line x*.
	 * @return Return EPI(y,t) with x*,s* as an two-dimensional array of RGB values
	 */
	private int[][] createVerticalEPI(File directory, int x)
	{
		File[] files=directory.listFiles();
		BufferedImage image=GraphicIO.getImage(files[0]);
		int[][] result=new int[files.length][image.getHeight()];
		for(int i=0;i<files.length;i++)
		{
			image=GraphicIO.getImage(files[i]);
			for(int y=0;y<image.getHeight();y++)
				result[i][y]=image.getRGB(x, y);
		}
		return result;
	}
	
	/**
	 * EPI line with y*,t*
	 * @param directory Directory with source images
	 * @param x Const EPI line y*.
	 * @return Return EPI(x,s) with y*,t* as an two-dimensional array of RGB values
	 */
	private int[][] createHorizontalEPI(File directory, int y)
	{
		File[] files=directory.listFiles();
		BufferedImage image=GraphicIO.getImage(files[0]);
		int[][] result=new int[files.length][image.getWidth()];
		for(int i=0;i<files.length;i++)
		{
			image=GraphicIO.getImage(files[i]);		
			for(int x=0;x<image.getWidth();x++)
				result[i][x]=image.getRGB(x, y);
		}
		return result;
	}
	
	/**
	 * Methods checking if EPI is HORIZONTAL or VERTICAL
	 * @return true if EPI is HORIZONTAL, false if VERTICAL
	 */
	public boolean isHorizontal()
	{
		return _position==Position.HORIZONTAL;
	}

	public static int[] loadConfig(File directory) 
	{
		int[] result=new int[4];
		File config=new File(directory, directory.getName()+"_config.txt");
		try 
		{
			Scanner scanner=new Scanner(config);
			result[0]=scanner.nextInt();
			result[1]=scanner.nextInt();
			result[2]=scanner.nextInt();
			result[3]=scanner.nextInt();
			scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return result;
	}

}
