package utils;

/**
 * Math supporting class. All methods are static.
 * @author Bartek
 * @version 1.1
 */
public class MathUtilities 
{
	
	/**
	 * Method calculating one-dimensional Gaussian smoothing operator.
	 * @param phi Parameter of Gaussian.
	 * @param size Length of the vector.
	 * @return Vector containing Gaussian smoothing operator values.
	 */
	public static double[] GaussianVector(double phi, int size)
	{
		return GaussianMatrix(phi, size, 1)[0];
	}
	
	/**
	 * Supporting method calculating Gaussian of any size.
	 * @param phi Parameter of Gaussian.
	 * @param width Length of Gaussian.
	 * @param height Height of Gaussian.
	 * @return Two-dimensional matrix containing Gaussian smoothing operator values.
	 */
	private static double[][] GaussianMatrix(double phi, int width, int height)
	{
		double[][] result=new double[width][height];
		double power;
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				power=(Math.pow((-width)/2+i,2)+Math.pow((-height)/2+j,2))/(2*Math.pow(phi, 2));
				result[i][j]=(double)(1/(2*Math.PI*phi*phi)*Math.pow(Math.E, -power));
			}
		}
		return result;
	}
	
	/**
	 * Method calculating square Matrix of Gaussian smoothing operator.
	 * @param phi Parameter of Gaussian.
	 * @param size Size of Gaussian, result is [size]x[size] matrix.
	 * @return Two-dimensional matrix of [size]x[size], containing Gaussian smoothing operator values.
	 */
	public static double[][] GaussianMatrix(double phi, int size)
	{
		return GaussianMatrix(phi, size, size);
	}
	
	/**
	 * Method calculating convolution.
	 * @param mask Matrix representing input mask.
	 * @param matrix Matrix representing the image.
	 * @return Two-dimensional matrix, result of convolution.
	 */
	public static double[][] convolution(double[][] mask, double[][] matrix)
	{
		double[][] result;
		result=new double[matrix.length][matrix[0].length];
		int width=matrix.length;
		int height=matrix[0].length;
		int w=(mask.length-1)/2;
		int h=(mask[0].length-1)/2;
		double sum=0.0;
		
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				sum=0.0;
				for(int i=-h;i<=h;i++)
				{
					for(int j=-w;j<=w;j++)
					{
						if(x-j>=0&&y-i>=0&&x-j<width&&y-i<height)
							sum+=mask[j+w][i+h]*matrix[x-j][y-i];
					}
				}
				result[x][y]=sum;				
			}
		}
		return result;
	}
	
	/**
	 * Method calculating convolution.
	 * @param mask Matrix representing input mask.
	 * @param matrix Matrix representing the image.
	 * @return Two-dimensional matrix, result of convolution.
	 */
	public static double[][] convolution(double[][] mask, int[][] matrix)
	{
		double[][] result;
		result=new double[matrix.length][matrix[0].length];
		int width=matrix.length;
		int height=matrix[0].length;
		int w=(mask.length-1)/2;
		int h=(mask[0].length-1)/2;
		double sum=0.0;
		
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				sum=0.0;
				for(int i=-h;i<=h;i++)
				{
					for(int j=-w;j<=w;j++)
					{
						if(x-j>=0&&y-i>=0&&x-j<width&&y-i<height)
							sum+=mask[j+w][i+h]*matrix[x-j][y-i];
					}
				}
				result[x][y]=sum;				
			}
		}
		return result;
	}


	/**
	 * Method normalizing matrix e.g. mask
	 * @param matrix Matrix to be normalized.
	 * @param positive Boolean indicating if values in matrix are only positive or not (different normalization)
	 * @return Normalized matrix.
	 */
	public static double[][] normalize(double[][] matrix, boolean positive) 
	{
		if(positive)
			return PositiveNormalize(matrix);
		else
			return NegativeNormalize(matrix);
	}
	
	/**
	 * Method normalizing matrix e.g. mask with only positive values
	 * @param matrix Matrix to be normalized.
	 * @return Normalized matrix.
	 */
	public static double[][] PositiveNormalize(double[][] matrix)
	{
		int width=matrix.length;
		int height=matrix[0].length;
		double[][] result=new double[width][height];
		double sum=0.0;
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				sum+=matrix[i][j];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				result[i][j]=matrix[i][j]/sum;
		return result;
	}
	
	/**
	 * Method normalizing matrix e.g. mask with negative values
	 * @param matrix Matrix to be normalized.
	 * @return Normalized matrix.
	 */
	public static double[][] NegativeNormalize(double[][] matrix) 
	{
		int width=matrix.length;
		int height=matrix[0].length;
		double max=matrix[0][0];
		double min=matrix[0][0];
		double[][] result=new double[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
			{
				max=Math.max(max, matrix[i][j]);
				min=Math.min(min, matrix[i][j]);
			}
		double dif=max-min;
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				result[i][j]=(matrix[i][j]-min)/dif;
		return result;
	}

	/**
	 * Method transforming double matrix to int matrix.
	 * @param Matrix Two-dimensional double array representing the matrix.
	 * @return Matrix with values transformed to integer.
	 */
	public static int[][] DoubleToInt(double[][] matrix) 
	{
		int[][] result=new int[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
				result[i][j]=(int) matrix[i][j];
		return result;
	}

	/**
	 * Matrix multiply by scalar
	 * @param value Multiplier
	 * @param matrix Matrix to be multiplied
	 * @return Multiplied matrix
	 */
	public static double[][] matrixMultiply(double value, double[][] matrix) 
	{
		double[][] result=new double[matrix.length][matrix[0].length];
		for(int i=0;i<result.length;i++)
			for(int j=0;j<result[0].length;j++)
				result[i][j]=value*matrix[i][j];
		return result;
	}
	
	/**
	 * Method transforming int matrix to double matrix.
	 * @param Matrix Two-dimensional int array representing the matrix.
	 * @return Matrix with values transformed to double.
	 */
	public static double[][] IntToDouble(int[][] matrix) 
	{
		double[][] result=new double[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
				result[i][j]=matrix[i][j];
		return result;
	}
}
