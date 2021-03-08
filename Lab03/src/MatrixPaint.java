import java.lang.reflect.Array;
public class MatrixPaint {
    public MatrixPaint(int[][] array) {
        for (int[] row : array) {
            for (int x : row)
                System.out.print(x + " ");
            System.out.println();
        }
        System.out.println();
    }
}
