//Source: https://adityasridhar.com/posts/how-to-use-java-executor-framework-for-multithreading
import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += i;
		}
		return sum;
	}

}