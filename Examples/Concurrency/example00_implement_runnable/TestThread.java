/*Source: www.tutorialspoint.com/java/java_multithreading.htm
The threads count from 4 down to 1 then exit.
Each time they run, the output will be printed in a different order depending on the scheduling.
*/
public class TestThread
{
   public static void main(String args[])
   {
      System.out.println("\nCreate two threads that count down from 4 to 1 then exit.\n");
      RunnableDemo R1 = new RunnableDemo( "Thread-A");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread-B");
      R2.start();
   }
}