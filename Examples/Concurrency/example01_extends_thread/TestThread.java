/*Source: www.tutorialspoint.com/java/java_multithreading.htm

Same as the previous example, but creating threads by creating a new class that extends Thread class.

This approach provides more flexibility in handling multiple threads created using available methods in Thread class.
*/
public class TestThread {

   public static void main(String args[]) {
      ThreadDemo T1 = new ThreadDemo( "Thread-A");
      T1.start();
      
      ThreadDemo T2 = new ThreadDemo( "Thread-B");
      T2.start();
   }   
}