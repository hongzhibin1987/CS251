
public class Exercise1Test
{
	public static void main(String[] args)
	{
		//Create an instance of the TShirt class
		//Same as saying "create a TShirt object"
		TShirt my_shirt = new TShirt(8, "blue");

		System.out.println("This size "+my_shirt.getSize()+" shirt is "+my_shirt.getColor());

		my_shirt.dye("green");

		System.out.println("Oops, now the shirt is "+my_shirt.getColor());
	}
}
