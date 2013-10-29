package utils;

import enums.GradientMask;
import enums.GreyMethod;

/**
 * Supporting graphic methods
 * @author Bartek
 * @version 1.0
 */
public class GraphicUtilities 
{
	public static double[][] gradient(GradientMask mask, int[][] image)
	{
		return MathUtilities.convolution(MathUtilities.normalize(mask.getMask()), image);		
	}
	
	/**
	 * Method summing matrixes pixel-wise.
	 * @param A first matrix
	 * @param B second matrix
	 * @return Pixel sum of matrixes
	 */
	public static double[][] pixelAdd(double[][] A, double[][] B)
	{
		double[][] result=new double[A.length][A[0].length];
		for(int i=0;i<A.length;i++)
			for(int j=0;j<A[0].length;j++)
				result[i][j]=A[i][j]+B[i][j];
		return result;				
	}
	
	/**
	 * Method subtracting matrixes pixel-wise.
	 * @param A first matrix
	 * @param B second matrix
	 * @return Pixel sum of matrixes
	 */
	public static double[][] pixelSubstract(double[][] A, double[][] B)
	{
		double[][] result=new double[A.length][A[0].length];
		for(int i=0;i<A.length;i++)
			for(int j=0;j<A[0].length;j++)
				result[i][j]=A[i][j]-B[i][j];
		return result;				
	}
	
	/**
	 * Method multiplying matrixes pixel-wise.
	 * @param A first matrix
	 * @param B second matrix
	 * @return Pixel sum of matrixes
	 */
	public static double[][] pixelMultiply(double[][] A, double[][] B)
	{
		double[][] result=new double[A.length][A[0].length];
		for(int i=0;i<A.length;i++)
			for(int j=0;j<A[0].length;j++)
				result[i][j]=A[i][j]*B[i][j];
		return result;				
	}
	
	/**
	 * Method multiplying matrixes pixel-wise.
	 * @param A first matrix
	 * @param B second matrix
	 * @return Pixel divide of matrixes
	 */
	public static double[][] pixelDivide(double[][] A, double[][] B)
	{
		double[][] result=new double[A.length][A[0].length];
		for(int i=0;i<A.length;i++)
			for(int j=0;j<A[0].length;j++)
				result[i][j]=A[i][j]/(B[i][j]+1);
		return result;				
	}
	
	/**
	 * Method transforming from RGB to greyscale.
	 * @param pixel - input pixel as int
	 * @param method - method to transform with (AVERAGE, LIGHTNESS, LUMINOSITY)
	 * @return
	 */
	public static int toGreyScale(int pixel, GreyMethod method)
	{
		int greyResult = 0;
		//int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		if(method==GreyMethod.AVERAGE)
			greyResult=(red+green+blue)/3;
		if(method==GreyMethod.LIGHTNESS)
			greyResult=(max(red, green, blue)+min(red,green,blue))/2;
		if(method==GreyMethod.LUMINOSITY)
			greyResult=(int)(0.21*red + 0.71*green + 0.07*blue);
		return greyResult;
		
	}
	
	public static int[][] toGreyScale(int[][] matrix, GreyMethod method)
	{
		int[][] result=new int[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
				result[i][j]=toGreyScale(matrix[i][j], method);
		return result;
	}

	private static int min(int red, int green, int blue) 
	{
		return Math.max(Math.max(red, green), blue);
	}

	private static int max(int red, int green, int blue) 
	{
		return Math.min(Math.min(red, green), blue);
	}

}
