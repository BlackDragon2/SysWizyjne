package algorithms;

import java.io.File;

public class CDL extends Algorithm 
{

	@Override
	public int[][] transform(File[] files) 
	{
		createEPI(files, Position.HORIZONTAL);
		EPILine epi=new EPILine(files, 500, Position.HORIZONTAL);
		return epi.get_pixels();
	
	}

}
