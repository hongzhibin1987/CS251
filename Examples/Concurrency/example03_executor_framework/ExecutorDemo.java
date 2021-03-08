/*Source: https://adityasridhar.com/posts/how-to-use-java-executor-framework-for-multithreading

The following is the second example (after the colorful diagram) on the above website.

Use executor framework to create a threadpool with 4 threads. Then create 5 callable Workers and submit them to the thread pool.

Use futures to check on the progress of the workers and get the result when they are done.

Each worker just adds up the numbers 0 to 4 inclusive.
*/
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo
{
	public static void main(String[] args)
	{
		ExecutorService executors = Executors.newFixedThreadPool(4);
		Future<Integer>[] futures = new Future[5];
		Callable<Integer> w = new Worker();
		try {
			//Submit all the work to the executor
			//and receive IOUs (Futures) from executor.
			for (int i = 0; i < 5; i++) {
				Future<Integer> future = executors.submit(w);
				futures[i] = future;
			}

			//Loop through and get the results that were promised or owed earlier.
			for (int i = 0; i < futures.length; i++) {
				try {
					System.out.println("Result from Future " + i + ":" + futures[i].get());
				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (ExecutionException e) {

					e.printStackTrace();
				}
			}
		} finally {
			executors.shutdown();
		}

	}

}