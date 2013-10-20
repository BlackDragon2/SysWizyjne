package algorithms;

import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public EPILine(BufferedImage[] images, int line, Position position)
	{
		createEPILine(images, line, position);
	}
	
	public EPILine(File directory, int line, Position position)
	{
		String name=directory.getName();
		File file=new File(directory.getAbsolutePath()+"\\"+name+"_"+line+"_"+position.toString()+".txt");
		if(file.exists())
			loadEPILine(file);
		else
			createEPILine(GraphicIO.getImages(directory.listFiles()), line, position);
			
	}
	
	private void createEPILine(BufferedImage[] images, int line, Position position) 
	{
		_position=position;
		_line=line;
		_pixels=position==Position.HORIZONTAL? createHorizontalEPI(images, line):createVerticalEPI(images, line);
	}

	public void saveEPILine(File directory)
	{
		
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
		_line=Integer.parseInt(split[1]);
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
	private int[][] createVerticalEPI(BufferedImage[] images, int x)
	{
		int[][] result=new int[images.length][images[0].getHeight()];
		for(int i=0;i<images.length;i++)
			for(int y=0;y<images[0].getHeight();y++)
				result[i][y]=images[i].getRGB(x, y);
		return result;
	}
	
	//EPI line with y*,t*
	private int[][] createHorizontalEPI(BufferedImage[] images, int y)
	{
		int[][] result=new int[images.length][images[0].getWidth()];
		for(int i=0;i<images.length;i++)
			for(int x=0;x<images[0].getWidth();x++)
				result[i][x]=images[i].getRGB(x, y);
		return result;
	}
	
	public boolean isHorizontal()
	{
		return _position==Position.HORIZONTAL;
	}

}
