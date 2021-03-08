/*
CSCI 2501
Zhibin "Bing" Hong
zhong@cnm.edu
Lab03: ConcurrentProcessing
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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

        A = B = C = new int[numberofRow][numberofCol];

        // Read matrix data into matrix A, B and C
        for (int r = 0; r < numberofRow; r++) {
            for (int c = 0; c < numberofCol; c++) {
                A[r][c] = sc.nextInt();
            }
        }

        // System.out.pirint Matrix A;
        System.out.println("Matrix A");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        // calculate Matrix B
        for (int r = 0; r<numberofRow; r++){
            for (int c = 0; c<numberofCol; c++){
                B[r][c] = sc.nextInt();
            }
        }

        System.out.println("Matrix B");
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Matrix A");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }











        /*
        try {
            for (int r = 0; r < numberofRow; r++) {
                for (int c = 0; c < numberofCol; c++ ){
                    A[r][c] = sc.nextInt();
                }
            }
            for (int r = 0; r < numberofRow; r++) {
                for (int c = 0; c < numberofCol; c++) {
                    B[r][c] = sc.nextInt();
                }
            }
        }

        catch (ArrayIndexOutOfBoundsException e) {}
        //

        }

        System.out.println("Matrix B");
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }



/*










        // break arraylist into 4. a total of 4*3+3 = 15 arraylists.
        int [] [] A00; int [] [] A01; int [] [] A10; int [] [] A11;
        int [] [] B00; int [] [] B01; int [] [] B10; int [] [] B11;
        int [] [] C00; int [] [] C01; int [] [] C10; int [] [] C11;

        // matrix splits
        A00 = new int[B.length/2][B[0].length/2];

        for (int r = 0; r < numberofRow/2; r++) {
            for (int c = 0; c < numberofCol/2; c++){
                A00[r][c] = A[r][c];

            }
        }
        System.out.println("MatrixA00");
        for (int i = 0; i < A00.length; i++) {
            for (int j = 0; j < A00.length; j++) {
                System.out.print(A00[i][j] + " ");
            }
        }

    }

         */
        }
    }
