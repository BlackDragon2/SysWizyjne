package enums;

/**
 * Enum class representing possible gradient masks.
 * @author Bartek
 * @version 1.0
 */
public enum GradientMask 
{
	ROBERTSX(new double[][]{{0,0,0},{-1,0,0},{0,1,0}}),
	ROBERTSY(new double[][]{{0,0,0},{0,0,-1},{0,1,0}}),
	SOBELX(new double[][]{{-1,-2,-1},{0,0,0},{1,2,1}}),
	SOBELY(new double[][]{{-1,0,1},{-2,0,2},{-1,0,1}}),
	PREWITTX(new double[][]{{-1,-1,-1},{0,0,0},{1,1,1}}),
	PREWITTY(new double[][]{{-1,0,1},{-1,0,1},{-1,0,1}});
	
	private final double[][] _matrix;
	
	GradientMask(double[][] matrix)
	{
		_matrix=matrix;
	}
	
	public double[][] getMask()
	{
		return _matrix;
	}
}
