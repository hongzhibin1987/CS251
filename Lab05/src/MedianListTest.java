import java.util.ArrayList;
public class MedianListTest {
    ArrayList<Integer> values = new ArrayList<Integer>();

    void MedianList() {

    }

    public void push(int x) {
        values.add(x);
    }

    public int pop() {
        int popValue = peek();
        values.remove(popValue);
        return popValue;
    }

    public int peek() {
        int nextValueLocation;
        int nextValue;
        if (values.size() % 2 == 0) {
            nextValueLocation = (values.size() / 2);
        } else {
            nextValueLocation = values.size() / 2 + 1;
        }
        nextValue = values.indexOf(nextValueLocation);
        return nextValue;
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public String toString() {
        int n = values.size();
        if (n == 0) {
            return "[]";
            StringBuilder sb = new StringBuilder();
            for (int i = values.size() - 1; i == 0; i--) {
                int num = values.get(i);
                sb.append(num);
            }
            String result = sb.toString();
            return result;
        }
    }
}

