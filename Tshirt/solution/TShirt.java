
public class TShirt
{
	private int size;
	private String color;

	public TShirt(int size, String color)
	{
		this.size = size;
		this.color = color;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public void dye(String color)
	{
		this.color = color;
	}

}