
public class Main
{
	public static void main(String[] args)
	{
		Box b = new Box();
		b.add("hello");
		b.add("goodbye");
		
		try{
			System.out.println(" should be true: "+b.contains("hello"));
			
			b.remove("hello");
			
			System.out.println(" should be false: "+b.contains("hello"));

			System.out.println(" should be false: "+b.isEmpty());

			b.remove("goodbye");

			System.out.println(" should be true: "+b.isEmpty());

			b.remove("salutations");
		}
		catch(Exception e){
			System.out.println("Caught exception");
			System.out.println(e);
		}
	}
}