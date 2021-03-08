import java.util.concurrent.Callable;

//Source: stackabuse.com/concurrency-in-java-the-executor-framework/
public class Task implements Callable<String> {

    private String message;

    public Task(String message) {
        this.message = message;
    }

    @Override
    public String call() throws Exception {
        return "Hello " + message + "!";
    }
}