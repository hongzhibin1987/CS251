/*
CSCI 2501
Zhibin "Bing" Hong
zhong@cnm.edu
Lab03: ConcurrentProcessing
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixMain {

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        // read file
        File file = new File("src/matrix.txt");
        Integer numberofRow;
        Integer numberofCol;
        Scanner sc = new Scanner(file);

        // determine number of row and col

        numberofRow = sc.nextInt();
        numberofCol = sc.nextInt();
        System.out.println(numberofRow);
        System.out.println(numberofCol);

        // Creating Array A and B with number of Col and number of row,
        int[][] A;
        int[][] B;

        if (numberofRow % 2 !=0){

        }

        A = new int[numberofRow][numberofCol];
        B = new int[numberofRow][numberofCol];

        // Read matrix data into matrix A, and
        for (int r = 0; r < numberofRow; r++) {
            for (int c = 0; c < numberofCol; c++) {
                A[r][c] = sc.nextInt();
            }
        }

        // System.out.print Matrix A;
        System.out.println("Matrix A");
        new MatrixPrint(A);

        // calculate Matrix B
        for (int r = 0; r < numberofRow; r++) {
            for (int c = 0; c < numberofCol; c++) {
                B[r][c] = sc.nextInt();
            }
        }

        // System.out.print Matrix B
        System.out.println("Matrix B");
        new MatrixPrint(B);

        // determine whether the row and col is odd/even. if odd, add modifier.
        int rowmod = 0; int colmod = 0;
        if (numberofRow %2 !=0) {
            rowmod =1;
        }
        if (numberofCol %2 !=0) {
            colmod =1;
        }

        // initiate new # or col and row
        int newRow = numberofRow / 2 + rowmod;
        int newCol = numberofCol / 2 + colmod;

        System.out.print(newRow +"\n");
        System.out.print(newCol +"\n");


        // break arraylist into 4. a total of 4*3+3 = 15 arraylists.
        int[][] A00 = new int[newRow][newCol];
        int[][] A01 = new int[newRow][newCol];
        int[][] A10 = new int[newRow][newCol];
        int[][] A11 = new int[newRow][newCol];
        int[][] B00 = new int[newRow][newCol];
        int[][] B01 = new int[newRow][newCol];
        int[][] B10 = new int[newRow][newCol];
        int[][] B11 = new int[newRow][newCol];
        int[][] C00;
        int[][] C01;
        int[][] C10;
        int[][] C11;

        // get data into matrix
        for (int r = 0; r < newRow; r++) {
            for (int c = 0; c < newCol; c++){
                A00[r][c] = A[r][c];
                A01[r][c] = A[r][c+newCol-colmod];
                new MatrixPrint(A01);
                A10[r][c] = A[r+newRow-rowmod][c];
                A11[r][c] = A[r+newRow-rowmod][c+newCol-colmod];

                B00[r][c] = B[r][c];
                B01[r][c] = B[r][c+newCol-colmod];
                B10[r][c] = B[r+newRow-rowmod][c];
                B11[r][c] = B[r+newRow-rowmod][c+newCol-colmod];
            }
        }

        // calculate
        MatrixCalc AB00 = new MatrixCalc(A00, B00);
        MatrixCalc AB01 = new MatrixCalc(A01, B01);
        MatrixCalc AB10 = new MatrixCalc(A10, B10);
        MatrixCalc AB11 = new MatrixCalc(A11, B11);

        // start process
        AB00.start();
        AB01.start();
        AB10.start();
        AB11.start();

        // depends on the timing, join calculation
        AB00.join();
        AB01.join();
        AB10.join();
        AB11.join();

        // get the final result
        C00=AB00.getResult();
        C01=AB01.getResult();
        C10=AB10.getResult();
        C11=AB11.getResult();

        // final matrix merge.
        new MatrixMerge(C00, C01, C10, C11, newRow, newCol, rowmod, colmod);

    }
}

class MatrixMerge {
    public MatrixMerge(int[][]C00, int[][]C01, int[][]C10, int[][]C11, int newRow, int newCol, int rowmod, int colmod){
        int m = C00.length;
        int n = C01.length;
        int p = C00[0].length;
        int q = C01[0].length;
        int[][]C0001 = new int[m][p+q];
        for (int i=0; i<m; i++){
            for(int j = 0; j<p+q; j++)
            {
                if (j<p){
                    C0001[i][j] = C00[i][j];
                }
                else
                {
                    C0001[i][j] = C01[i][j-p];
                }
            }
        }

        int a = C10.length;
        int b = C11.length;
        int c = C10[0].length;
        int d = C11[0].length;
        int[][]C1011 = new int[m][p+q];
        for (int i=0; i<a; i++){
            for(int j = 0; j<c+d; j++)
            {
                if (j<c){
                    C1011[i][j] = C10[i][j];
                }
                else
                {
                    C1011[i][j] = C11[i][j-c];
                }
            }
        }

        int[][] C = new int[m+n-rowmod][p+q-colmod];
        for (int i = 0; i<newRow; i++){
            for (int j = 0; j<newCol; j++){
                C[i][j] = C00[i][j];
                C[i][j+newCol-colmod] = C01[i][j];
                C[i+newRow-rowmod][j] = C10[i][j];
                C[i+newRow-rowmod][j+newCol-colmod] = C11[i][j];
            }
        }
        System.out.println("Matrix A + Matrix B");
        new MatrixPrint(C);
    }
}

// print out matrix (mainly for de-bugging purpose)
class MatrixPrint {
    public MatrixPrint(int[][] array) {
        for (int[] row : array) {
            for (int x : row)
                System.out.print(x + " ");
            System.out.println();
        }
        System.out.println();
    }
}

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