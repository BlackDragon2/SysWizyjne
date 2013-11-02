package algorithms;

import enums.GradientMask;
import enums.GreyMethod;
import enums.Position;
import graphicIO.GraphicIO;

import java.awt.image.BufferedImage;
import java.io.File;

import utils.GraphicUtilities;
import utils.MathUtilities;

/**
 * Realization of CDL algorithm, extending class algorithm and implementing transform method.
 * @author Bartek
 * @version 1.0
 */
public class CDL extends Algorithm 
{
	
	private static final double _PHI=1;
	private static final int _GAUSSIAN_SIZE=3;
	private static final GradientMask _MASKX=GradientMask.ROBERTSX;
	private static final GradientMask _MASKY=GradientMask.ROBERTSY;

	@Override
	public int[][] transform(File directory, Position position, double focalPoint) 
	{
		//config 0 - image width 1 - image height 2 - number of horizontal images 3 - number of vertical images
		int[] config=EPILine.loadConfig(directory);
		int width=config[0];
		int height=config[1];
		int horNr=config[2];
		int verNr=config[3];
		EPILine epi;
		double[][] Jxx;
		double[][] Jxy;
		double[][] Jyy;
		double[][] dX;
		double[][] dS;
		double[][] l;
		double[][] r;
		if(position==Position.HORIZONTAL||position==Position.BOTH)
		{
			for(int i=0;i<height;i++)
			{
				epi=new EPILine(directory, i, Position.HORIZONTAL);
				
				//equation(4)
				double[][] gradientX=GraphicUtilities.gradient(_MASKX, epi.get_pixels());
				double[][] gradientY=GraphicUtilities.gradient(_MASKY, epi.get_pixels());
				double[][] gaussian=MathUtilities.normalize(MathUtilities.GaussianMatrix(_PHI, _GAUSSIAN_SIZE), true);
				Jxx=MathUtilities.convolution(gaussian, GraphicUtilities.pixelMultiply(gradientX, gradientX));
				Jxy=MathUtilities.convolution(gaussian, GraphicUtilities.pixelMultiply(gradientX, gradientY));
				Jyy=MathUtilities.convolution(gaussian, GraphicUtilities.pixelMultiply(gradientY, gradientY));
				
				//equation(5)
				dX=GraphicUtilities.pixelSubstract(Jyy, Jxx);
				dS=GraphicUtilities.pixelMultiply(2.0, Jxy);
				
				//equation(6)
				l=GraphicUtilities.pixelMultiply(focalPoint, GraphicUtilities.pixelDivide(dS, dX));
			}
		}
		if(position==Position.HORIZONTAL||position==Position.BOTH)
		{
			
		}

		return null;	
	}

}
