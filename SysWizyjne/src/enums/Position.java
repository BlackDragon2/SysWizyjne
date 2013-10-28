package enums;

/**
 * Enum class representing Axis (orientation) along which EPIs are created.
 * @author Bartek
 * @version 1.0
 */
public enum Position 
{
	HORIZONTAL("HORIZONTAL"),
	VERTICAL("VERTICAL"),
	BOTH("BOTH");
	
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
