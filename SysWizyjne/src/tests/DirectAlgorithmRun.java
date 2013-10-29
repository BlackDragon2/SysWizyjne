package tests;

import java.io.File;

import enums.Position;
import algorithms.CDL;

public class DirectAlgorithmRun {

	public static void main(String[] args) 
	{
		CDL cld=new CDL();
		//cld.compute(new File("C:\\Users\\Bartek\\Desktop\\semest IX\\wizyjne\\bikes_image-raw\\bikes_image-raw"), Position.HORIZONTAL);
		cld.compute(new File("D:\\download\\car"), Position.HORIZONTAL);
	}

}
