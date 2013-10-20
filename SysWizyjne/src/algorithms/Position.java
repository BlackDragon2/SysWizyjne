package algorithms;

public enum Position 
{
	HORIZONTAL("Horizontal"),
	VERTICAL("Vertical");
	
	private final String name;
	
	Position(String n)
	{
		name=n;
	}
	
	public String toString()
	{
		return name;
	}
}
