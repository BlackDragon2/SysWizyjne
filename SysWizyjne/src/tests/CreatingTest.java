package tests;

import java.io.File;

import enums.Position;
import algorithms.Algorithm;

public class CreatingTest 
{
	public static void Main(String[] args)
	{
		test("D:\\download\\car");
	}
	
	public static void test(String dir)
	{
		File file;
		boolean result=true;
		for(int i=0;i<2592;i++)
		{
			file=new File(dir+"\\car_"+i+"_HORIZONTAL");
			if(file.exists())
				file.delete();
		}
		for(int i=0;i<3888;i++)
		{
			file=new File(dir+"\\car_"+i+"_VERTICAL");
			if(file.exists())
				file.delete();
		}
		
		Algorithm.createEPI(new File(dir), Position.BOTH);
		
		for(int i=0;i<2592&&result;i++)
		{
			file=new File(dir+"car_"+i+"_HORIZONTAL");
			if(!file.exists())
				result=false;
		}
		for(int i=0;i<3888&&result;i++)
		{
			file=new File(dir+"car_"+i+"_VERTICAL");
			if(!file.exists())
				result=false;
		}
		
		if(result)
		{
			System.out.println("Creation done");
		}
		else
		{
			System.out.println("Creation error");
		}
	}
}
