import java.io.*; 
import java.util.*; 

public class ThreadedDeposit extends Thread
{
	private BankAccount account; 

	//Constructor
	public ThreadedDeposit(BankAccount account) 
	{ 
		this.account = account; 
	} 
  
	public void run() 
	{
		//Deposit 1 million nickels
		for(int i=0; i<1000000; i++)
			this.account.deposit(5);
	} 
}