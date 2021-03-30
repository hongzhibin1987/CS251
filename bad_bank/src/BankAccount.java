public class BankAccount
{
	private int every_last_penny;
	
	//Constructor
	public BankAccount(int amount)
	{
		this.every_last_penny = amount;
	}
	
	public int getAmount(){ return every_last_penny; }

	// Part B. Modify the timed code to have fine-grained synchronization by adding two generic objects, one to lock the modification of this.every_last_penny in the BankAccount.
	// deposit method and the other to lock modification of this.every_last_penny in the BankAccount.withdraw method. Use the example in Monitors_and_Locks.docx to help guide you in performing this modification.
	// Afterward, your code should now display the correct amount in the account when run. In other words, bad bank should now be a good bank.
	public void deposit(int amount) 
	{
		synchronized (this){
			this.every_last_penny += amount;
		}
	}
	
	public void withdraw(int amount) 
	{
		synchronized (this) {
			this.every_last_penny -= amount;
		}

	}
}