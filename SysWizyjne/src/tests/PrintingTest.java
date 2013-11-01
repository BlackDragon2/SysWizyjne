package tests;

import enums.GradientMask;
import enums.GreyMethod;
import enums.Position;
import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.File;

import utils.GraphicUtilities;
import utils.MathUtilities;
import algorithms.EPILine;

public class PrintingTest 
{
	public static void Main(String[] args)
	{
		test("D:\\download\\car");
	}

	public static void test(String dir) 
	{
		File file=new File(dir);
		EPILine epi;
		int[][] horImage=new int[3888][2592];
		int[][] verImage=new int[3888][2592];
		System.out.println("Starting creation of horizontal image");
		for(int i=0;i<2592;i++)
		{
			epi=new EPILine(file, i, Position.HORIZONTAL);
			for(int j=0;j<3888;j++)
				horImage[j][i]=epi.get_pixels()[0][j];		
		}
		System.out.println("Starting creation of vertiocal image");
		for(int i=0;i<3888;i++)
		{
			epi=new EPILine(file, i, Position.VERTICAL);
			for(int j=0;j<2592;j++)
				verImage[i][j]=epi.get_pixels()[0][j];		
		}
		GraphicIO.saveImage(GraphicIO.createImage(horImage),new File("D:\\images\\imageHor"+System.currentTimeMillis()+".jpg"));
		System.out.println("Horizontal image done");
		GraphicIO.saveImage(GraphicIO.createImage(verImage),new File("D:\\images\\imageVer"+System.currentTimeMillis()+".jpg"));
		System.out.println("Vertical image done");
		epi=new EPILine(file, 1000, Position.HORIZONTAL);
		EPILine epi2=new EPILine(file, 2000, Position.HORIZONTAL);
		EPILine epi3=new EPILine(file, 1000, Position.VERTICAL);
		EPILine epi4=new EPILine(file, 2000, Position.VERTICAL);
		GraphicIO.saveImage(GraphicIO.createImage(epi.get_pixels(),Position.HORIZONTAL),new File("D:\\images\\image1000Hor"+System.currentTimeMillis()+".jpg"));
		GraphicIO.saveImage(GraphicIO.createImage(epi2.get_pixels(),Position.HORIZONTAL),new File("D:\\images\\image2000Hor"+System.currentTimeMillis()+".jpg"));
		GraphicIO.saveImage(GraphicIO.createImage(epi3.get_pixels(),Position.VERTICAL),new File("D:\\images\\image1000Ver"+System.currentTimeMillis()+".jpg"));
		GraphicIO.saveImage(GraphicIO.createImage(epi4.get_pixels(),Position.VERTICAL),new File("D:\\images\\image2000Ver"+System.currentTimeMillis()+".jpg"));
		System.out.println("EPI images done");
	}
	
	public static void test2(String dir, GreyMethod method)
	{
		File file=new File(dir);
		EPILine epi;
		int[][] horImage=new int[3888][2592];
		int[][] horImage2;
		System.out.println("Starting creation of horizontal image");
		for(int i=0;i<2592;i++)
		{
			epi=new EPILine(file, i, Position.HORIZONTAL);
			for(int j=0;j<3888;j++)
				horImage[j][i]=epi.get_pixels()[0][j];		
		}
		GraphicIO.saveImage(GraphicIO.createGreyscaleImage(horImage, method),new File("D:\\images\\imageHor"+System.currentTimeMillis()+".jpg"));
		System.out.println("Horizontal image done");
		System.out.println("Starting gradient creation");
		horImage=GraphicUtilities.toGreyScale(horImage, GreyMethod.AVERAGE);
		horImage2=MathUtilities.DoubleToInt(GraphicUtilities.gradient(GradientMask.PREWITTX, horImage));
		GraphicIO.saveImage(GraphicIO.createImage(horImage2, BufferedImage.TYPE_BYTE_GRAY), "D:\\images\\xPRE.jpg");
		horImage2=MathUtilities.DoubleToInt(GraphicUtilities.gradient(GradientMask.PREWITTY, horImage));
		GraphicIO.saveImage(GraphicIO.createImage(horImage2, BufferedImage.TYPE_BYTE_GRAY), "D:\\images\\yPRE.jpg");
		System.out.println("Prewitt done");
		horImage2=MathUtilities.DoubleToInt(GraphicUtilities.gradient(GradientMask.ROBERTSX, horImage));
		GraphicIO.saveImage(GraphicIO.createImage(horImage2, BufferedImage.TYPE_BYTE_GRAY), "D:\\images\\xROB.jpg");
		horImage2=MathUtilities.DoubleToInt(GraphicUtilities.gradient(GradientMask.ROBERTSY, horImage));
		GraphicIO.saveImage(GraphicIO.createImage(horImage2, BufferedImage.TYPE_BYTE_GRAY), "D:\\images\\yROB.jpg");
		System.out.println("Roberts done");
		horImage2=MathUtilities.DoubleToInt(GraphicUtilities.gradient(GradientMask.SOBELX, horImage));
		GraphicIO.saveImage(GraphicIO.createImage(horImage2, BufferedImage.TYPE_BYTE_GRAY), "D:\\images\\xSOB.jpg");
		horImage2=MathUtilities.DoubleToInt(GraphicUtilities.gradient(GradientMask.SOBELY, horImage));
		GraphicIO.saveImage(GraphicIO.createImage(horImage2, BufferedImage.TYPE_BYTE_GRAY), "D:\\images\\ySOB.jpg");
		System.out.println("Sobel done");
	}
}
