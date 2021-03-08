// note: https://www.javaprogramto.com/2020/01/java-matrix-multiplication-threads.html
class MatrixCalc extends Thread {
    private int[][] A;
    private int[][] B;
    int[][] C;

    public MatrixCalc(int[][]A, int[][]B){
        this.A = A;
        this.B = B;
        C = new int[A.length][A[0].length];
    }
    @Override
    public void run(){
        for (int r = 0; r<A.length; r++){
            for (int c = 0; c<A[r].length; c++) {
                C[r][c] = A[r][c] + B[r][c];
            }
        }
    }
    public int[][] getResult(){
        return C;
    }
}