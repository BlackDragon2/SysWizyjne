package algorithms;

import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EPILine 
{
	private Position _position;
	private int[][] _pixels;
	private int _line;
	
	
	public EPILine(int[][] pixels, int line, Position position)
	{
		_position=position;
		_line=line;
		_pixels=pixels;
		
	}
	
	public EPILine(File directory, int line, Position position)
	{
		String name=directory.getName();
		File file=new File(directory.getAbsolutePath(),name.replace("_", "")+"_"+line+"_"+position.toString()+".txt");
		if(file.exists())
			loadEPILine(file);
		else
			createEPILine(directory.listFiles(), line, position);			
	}
	
	public EPILine(File[] files, int line, Position position) 
	{
		createEPILine(files, line, position);
	}

	private void createEPILine(File[] files, int line, Position position) 
	{
		_position=position;
		_line=line;
		_pixels=position==Position.HORIZONTAL? createHorizontalEPI(files, line):createVerticalEPI(files, line);
	}

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
			int i=0;
			int j=0;
			while(scanner.hasNext())
			{
				result[i][j]=scanner.nextInt();
				j++;
				if(j==width)
				{
					i++;
					j=0;
				}
				
			}
			scanner.close();	
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_pixels=result;
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

	//EPI line with x*,s*
	private int[][] createVerticalEPI(File[] files, int x)
	{
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
	
	//EPI line with y*,t*
	private int[][] createHorizontalEPI(File[] files, int y)
	{
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
	
	public boolean isHorizontal()
	{
		return _position==Position.HORIZONTAL;
	}

}
