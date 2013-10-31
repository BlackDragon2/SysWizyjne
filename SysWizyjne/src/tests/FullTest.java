package tests;

import enums.GreyMethod;

public class FullTest {

	public static void main(String[] args) 
	{
		CreatingTest.test("D:\\download\\car");
		PrintingTest.test("D:\\download\\car");
		//PrintingTest.test2("D:\\download\\car", GreyMethod.LUMINOSITY);
		//PrintingTest.test2("D:\\download\\car", GreyMethod.AVERAGE);
		//PrintingTest.test2("D:\\download\\car", GreyMethod.LIGHTNESS);
	}

}
