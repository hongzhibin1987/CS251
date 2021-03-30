// A Java program to demonstrate working of synchronized. 
import java.io.*; 
import java.util.*; 

// Driver class
// Part A. Implement new code that times the current program from before the start of the two threads to the end of the try-catch surrounding join.
// Report the time it took for three different runs and the average in a comment at the top of the program.
// 1st: 5ms
// 2nd: 7ms
// 3rd: 5ms
// average of 3 = 5.66ms
// 4th: 64ms
// 5th: 41ms
// 6th: 61ms
// average of 3 = 55.3ms

//Part C. Run the code three more times and record the time it takes and the average. How much longer on average does correctness cost the program?
//(You shouldn't need to change the code at all for step 3 here.) Answer this question in a comment at the top of the program.

public class SyncDemo
{ 
    public static void main(String args[]) 
    {
        BankAccount account = new BankAccount(10); //10 cents 
        ThreadedDeposit depositer = new ThreadedDeposit(account); 
        ThreadedWithdraw withdrawer = new ThreadedWithdraw(account);

        long start = System.currentTimeMillis();
        // Start two threads
        depositer.start(); 
        withdrawer.start(); 
  
        // wait for threads to end 
        try
        {
			//When we invoke the join() method on a thread, the calling thread goes into a waiting state. It remains in a waiting state until the referenced thread terminates.
			//Source: www.baeldung.com/java-thread-join
            depositer.join(); 
            withdrawer.join(); 
        } 
        catch(Exception e) 
        { 
            System.out.println("Interrupted"); 
        }
        long end = System.currentTimeMillis();
		
		System.out.printf("Amount in account: %,d \u00A2 \n",account.getAmount());
		int expected = 10+5000000-1000000;
		System.out.printf("I expect to have %,d \u00A2 \n",expected);
		System.out.println((end-start)+"ms");
    } 
}