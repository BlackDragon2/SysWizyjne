package enums;

/**
 * Enum class representing possible gradient masks.
 * @author Bartek
 * @version 1.0
 */
public enum GradientMask 
{
	ROBERTSX(new int[][]{{0,0,0},{-1,0,0},{0,1,0}}),
	ROBERTSY(new int[][]{{0,0,0},{0,0,-1},{0,1,0}}),
	SOBELX(new int[][]{{-1,-2,-1},{0,0,0},{1,2,1}}),
	SOBELY(new int[][]{{-1,0,1},{-2,0,2},{-1,0,1}}),
	PREWITTX(new int[][]{{-1,-1,-1},{0,0,0},{1,1,1}}),
	PREWITTY(new int[][]{{-1,0,1},{-1,0,1},{-1,0,1}});
	
	private final int[][] _matrix;
	
	GradientMask(int[][] matrix)
	{
		_matrix=matrix;
	}
	
	public int[][] getMask()
	{
		return _matrix;
	}
}
