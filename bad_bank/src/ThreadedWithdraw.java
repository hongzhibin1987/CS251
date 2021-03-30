import java.io.*; 
import java.util.*; 

public class ThreadedWithdraw extends Thread
{
	private BankAccount account; 
	
	//Constructor
	public ThreadedWithdraw(BankAccount account) 
	{ 
		this.account = account; 
	} 
  
	public void run() 
	{
		//Withdraw 1 million pennies
		for(int i=0; i<1000000; i++)
			this.account.withdraw(1);
	} 
}