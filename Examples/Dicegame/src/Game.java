public class Game
{
	//TODO: Declare two private Die variables named player1 and player2 here. This provides a reference to Die from Game, which is an example of composition.
	private Die player1;
	private Die player2;

	public Game()
	{
		//TODO: Initialize the two Die variables here. DO NOT pass the number of sides to the Dice's constructors.
		player1 = new Die();
		player2 = new Die();
	}
	
	public void play()
	{
		int roll1 = player1.roll();
		int roll2 = player2.roll();
		if(roll1 > roll2)
		{
			System.out.println("Player 1 wins");
		}
		else
		{
			System.out.println("Player 2 wins");
		}
	}
}