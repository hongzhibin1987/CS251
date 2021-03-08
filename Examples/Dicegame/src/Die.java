import java.security.SecureRandom;
/* The difference between 
java.security.SecureRandom
and
java.util.Random
is described here
https://www.baeldung.com/java-secure-random
*/
public class Die
{
	private SecureRandom rand = new SecureRandom();
	private int sides;

	//TODO: Create an overloaded constructor for the Die that takes no arguments and creates a 6 sided die by default.
	public Die(){
		this(6); 
	}

	
	public Die(int sides)
	{
		rand.setSeed(System.currentTimeMillis());
		this.sides = sides;
	}
	
	public int roll()
	{
		return rand.nextInt() % this.sides + 1;
	}
}