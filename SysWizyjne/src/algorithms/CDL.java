package algorithms;

import enums.Position;
import graphicIO.GraphicIO;

import java.io.File;

/**
 * Realization of CDL algorithm, extending class algorithm and implementing transform method.
 * @author Bartek
 * @version 1.0
 */
public class CDL extends Algorithm 
{

	@Override
	public int[][] transform(File directory, Position position) 
	{
		EPILine epi=new EPILine(directory, 500, position);
		GraphicIO.saveImage(GraphicIO.createImage(epi.get_pixels()), new File("D:\\images\\x.jpg"));
		return null;
	
	}

}
