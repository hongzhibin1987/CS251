/*
CSCI 2501
Zhibin "Bing" Hong
zhong@cnm.edu
Lab03: ConcurrentProcessing
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixMain {

    public static void main(String args[]) throws FileNotFoundException {

        // read file
        File file = new File("src/matrix.txt");
        Integer numberofRow = null;
        Integer numberofCol = null;
        Scanner sc = new Scanner(file);

        // determine number of row and col

        numberofRow = sc.nextInt();
        numberofCol = sc.nextInt();
        System.out.println(numberofRow);
        System.out.println(numberofCol);

        // Creating Array A and B with number of Col and number of row, also create an empty arraylist C
        int[][] A;
        int[][] B;
        int[][] C;

        A = new int[numberofRow][numberofCol];
        B = new int[numberofRow][numberofCol];
        C = new int[numberofRow][numberofCol];
        // Read matrix data into matrix A, B and C
        for (int r = 0; r < numberofRow; r++) {
            for (int c = 0; c < numberofCol; c++) {
                A[r][c] = sc.nextInt();
            }
        }

        // System.out.print Matrix A;
        System.out.println("Matrix A");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        // calculate Matrix B
        for (int r = 0; r < numberofRow; r++) {
            for (int c = 0; c < numberofCol; c++) {
                B[r][c] = sc.nextInt();
            }
        }

        // System.out.print Matrix B
        System.out.println("Matrix B");
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }

        // initiate new # or col and row
        int Row00 = numberofRow / 2;
        int Col00 = numberofCol / 2;
        int Row01 = numberofRow - numberofRow / 2;
        int Col01 = numberofCol - numberofCol / 2;

        // break arraylist into 4. a total of 4*3+3 = 15 arraylists.
        int[][] A00 = new int[Row00][Col00];
        int[][] A01 = new int[Row00][Col01];
        int[][] A10 = new int[Row01][Col00];
        int[][] A11 = new int[Row01][Col01];
        int[][] B00 = new int[Row00][Col00];
        int[][] B01 = new int[Row00][Col01];
        int[][] B10 = new int[Row01][Col00];
        int[][] B11 = new int[Row01][Col01];
        int[][] C00;
        int[][] C01;
        int[][] C10;
        int[][] C11;

        for (int r = 0; r < Row00; r++) {
            for (int c = 0; c < Col00; c++) {
                A00[r][c] = A[r][c];
                A10[r][c] = A[r + Row00][c];
                B00[r][c] = B[r][c];
                B10[r][c] = B[r + Row00][c];
            }
        }

        if (Row00 % 2 != 0 || Col00 % 2 != 0) {
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

        MatrixCalc AB00 = new MatrixCalc(A00, B00);
        MatrixCalc AB01 = new MatrixCalc(A01, B01);
        MatrixCalc AB10 = new MatrixCalc(A10, B10);
        MatrixCalc AB11 = new MatrixCalc(A11, B11);

        AB00.start();
        AB01.start();
        AB10.start();
        AB11.start();

        C00=AB00.getResult();
        C01=AB01.getResult();
        C10=AB10.getResult();
        C11=AB11.getResult();

        new MatrixMerge(C00, C01, C10, C11);



    }
}