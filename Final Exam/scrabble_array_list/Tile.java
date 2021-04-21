
public class Tile
{
	//intance variables
	private char letter;
	private int value;
	
	private int count_setter = 0;

	//constructor
	public Tile(char letter, int value)
	{
		this.letter = letter;
		this.value = value;
	}
	
	public char getLetter(){ return letter; }

	public int getValue(){ return value; }
	
	public void setLetter(char new_letter)
	{
		this.letter = new_letter;
	}

	public void setValue(int new_value)
	{
		this.value = new_value;
	}
	
	@Override
	public String toString()
	{
		return "The letter "+letter+" is worth "+value+" points.";
	}

	@Override
	public boolean equals(Tile other)
	{
		if(letter == other.getLetter() && value == other.getValue())
			return true;
		else
			return false;
	}
	
}