/*Source: stackabuse.com/concurrency-in-java-the-executor-framework/

Java provides its own multi-threading framework called the Executor Framework.

The Executor Framework contains a bunch of components that are used to efficiently manage worker threads.

A basic question that comes to mind is why do we need such thread pools when we can create objects of java.lang.Thread or implement Runnable/Callable interfaces to achieve parallelism?

The answer comes down to two basic facts:

1. Creating a new thread for a new task leads to overhead of thread creation and tear-down. Managing this thread life-cycle significantly adds to the execution time.
2. Adding a new thread for each process without any throttling leads to the creation of a large number of threads. These threads occupy memory and cause wastage of resources. The CPU starts to spend too much time switching contexts when each thread is swapped out and another thread comes in for execution.

*/
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorExample
{
    /* Create one callable task and four worker threads waiting for work.
	
	Pass the task to the pool of threads.*/
    public static void main(String[] args)
	{
        Task task = new Task("World");

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> result = executorService.submit(task);

        try {
			String response = result.get();
            System.out.println(response);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occured while executing the submitted task");
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}