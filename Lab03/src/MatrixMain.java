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



/*
        // this was part of the old code to determine the size of the matrix and etc;
/*
        if (numberofRow % 2 !=0)
        for (int r = 0; r < Row00; r++) {
            for (int c = 0; c < Col00 +1; c++){
                A00[r][c] = A[r][c];
                //new MatrixPrint(A00);
                System.out.print((c+Col00) + "\n");
                A01[r][c] = A[r][c+Col00];
                new MatrixPrint(A01);
            }
        for (int r = 0; r < Row00; r++) {
            for (int c = 0; c < Col00; c++) {
                A00[r][c] = A[r][c];
                //new MatrixPrint(A00);
                A10[r][c] = A[r - Row00][c];
                new MatrixPrint(A10);
                B00[r][c] = B[r][c];
                B10[r][c] = B[r + Row00][c];
            }
        }
        if (Col00 % 2 != 0 || Col01 % 2 != 0) {
            for (int r = 0; r < Row01; r++) {
                for (int c = 0; c < Col01; c++) {
                    A01[r][c] = A[r][c + Col01 - 1];
                    A11[r][c] = A[r + Row01][c + Col01 - 1];
                    B01[r][c] = B[r][c + Col01 - 1];
                    B11[r][c] = B[r + Row01][c + Col01 - 1];
                }
            }
        } else
            for (int r = 0; r < Row01; r++) {
                for (int c = 0; c < Col01; c++) {
                    A01[r][c] = A[r][c + Col01];
                    A11[r][c] = A[r + Row01][c + Col01];
                    B01[r][c] = B[r][c + Col01];
                    B11[r][c] = B[r + Row01][c + Col01];
                }
            }
 */

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