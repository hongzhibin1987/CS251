// fig 7.2: AccountTest.java
// Creating and manipulating and Account object
import java.util.Scanner;

public class AccountTest
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in); // create a Scanner object to obtain input from the command window
		
		Account myAccount = new Account(); // create an NEW Account object and assign it to myAccount
		
		System.out.printf("Initial name is: %s%n%n", myAccount.getName()); //calling class Account's getName Method. 
		
		System.out.println("Please enter the name: "); // prompt for and read name; 
		
		String theName = input.nextLine(); // read a line of the text; 
		
		myAccount.setName(theName); // put theName in myAccount; 
		
		System.out.println(); 
		
		System.out.printf("Name in object myAccount is: %n%s%n", myAccount.getName());
	}
}// end class AccountTest 