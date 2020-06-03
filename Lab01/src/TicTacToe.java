// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Communit College
// Course: CSCI-2251, Summer 2020
// Author: Zhibin "Bing" Hong
// 06-01-2020

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe
{
    enum gameStatus{
        WIN, DRAW, CONTINUE
    }
    static ArrayList<Integer> Positionofplayer1 = new ArrayList<Integer>();
    static ArrayList<Integer> Positionofplayer2 = new ArrayList<Integer>();
    public static void main(String[] args)
    {
        String [] [] GameBoard = {
                {" ", "|", " ", "|", " "},
                {"-", "+", "-", "+", "-"},
                {" ", "|", " ", "|", " "},
                {"-", "+", "-", "+", "-"},
                {" ", "|", " ", "|", " "},
        };
        printBoard(GameBoard);

        while(true) {
            Scanner scan = new Scanner (System.in);
            System.out.println("Player1: Enter your row, (0, 1 or 2): ");
            int row = scan.nextInt();
            System.out.println("Player1: Enter your col, (0, 1 or 2): ");
            int col = scan.nextInt();
            int[][] position = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9},};
            char pos1 = (char) position[row][col];
            validMove(GameBoard, pos1, "Player1");
            printBoard(GameBoard);
            gameStatus();
            if (Positionofplayer1.containsAll(List)){

            }


            System.out.println("Player2: Enter your row, (0, 1 or 2): ");
            int row2 = scan.nextInt();
            System.out.println("Player2: Enter your col, (0, 1 or 2): ");
            int col2 = scan.nextInt();
            int[][] position2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9},};
            char pos2 = (char) position2[row2][col2];
            validMove(GameBoard, pos2, "Player2");
            printBoard(GameBoard);
            gameStatus();

        }
    }

    public static void printBoard(String[][] GameBoard){
        for (String[] row: GameBoard){
            for (String item: row){
                System.out.print(item);
            }
            System.out.println();
        }
    }

    public static void gameStatus() {
        List ROW1 = Arrays.asList(1,2,3);
        List ROW2 = Arrays.asList(4,5,6);
        List ROW3 = Arrays.asList(7,8,9);
        List COL1 = Arrays.asList(1,4,7);
        List COL2 = Arrays.asList(2,5,6);
        List COL3 = Arrays.asList(3,6,9);
        List CRO1 = Arrays.asList(1,5,9);
        List CRO2 = Arrays.asList(3,5,7);

        List<List> win = new ArrayList<List>();
        win.add(ROW1);
        win.add(ROW2);
        win.add(ROW3);
        win.add(COL1);
        win.add(COL2);
        win.add(COL3);
        win.add(CRO1);
        win.add(CRO2);

        for(List l: win) {
            if (Positionofplayer1.containsAll(l)){
                System.out.println("Player1 "+ gameStatus.WIN);
                break;
            } else if (Positionofplayer2.containsAll(l)){
                System.out.println("Player2 "+ gameStatus.WIN);
                break;
            } else if (Positionofplayer1.size()+Positionofplayer2.size() ==9){
                System.out.println("This game is "+gameStatus.DRAW);
                break;
            }
        }

        return;
    }

    public static void validMove (String[][] GameBoard, int pos, String user){
        String symbol = "";
        if(user.equals("Player1")) {
            symbol = "X";
            Positionofplayer1.add(pos);
        }
        else if(user.equals("Player2")){
            symbol = "O";
            Positionofplayer2.add(pos);
        }

        switch(pos){
            case 1:
                GameBoard[0][0] = symbol;
                break;
            case 2:
                GameBoard[0][2] = symbol;
                break;
            case 3:
                GameBoard[0][4] = symbol;
                break;
            case 4:
                GameBoard[2][0] = symbol;
                break;
            case 5:
                GameBoard[2][2] = symbol;
                break;
            case 6:
                GameBoard[2][4] = symbol;
                break;
            case 7:
                GameBoard[4][0] = symbol;
                break;
            case 8:
                GameBoard[4][2] = symbol;
                break;
            case 9:
                GameBoard[4][4] = symbol;
                break;
        }
    }


}