import java.util.ArrayList;
import java.util.Collections;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Tile> alphabet = new ArrayList<Tile>();
		for(int i=0; i<26; i++)
		{
			Tile t = new Tile((char)('a'+i), 5);
			alphabet.add(t);
		}
		
		Collections.shuffle(alphabet);

		System.out.println("\nThe following tiles are NOT in order:");
		for(Tile t : alphabet)
			System.out.println(t);

		Collections.sort(alphabet);
		Collections.reverse(alphabet);

		System.out.println("\nThe following tiles should be in order from a to z:\n");
		for(Tile t : alphabet)
			System.out.println(t);
	}
}