// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Communit College
// Course: CSCI-2251, Summer 2020
// Author: Zhibin "Bing" Hong
// 06-01-2020

import javax.swing.*;
import java.util.Scanner;
// Initiate public class TTT;
public class TicTacToe {
    //----------------------------------------- LOGIC-MAIN------------------------------

    public TicTacToe(String PLAYER1, String PLAYER2) {

        // create a new object from CELL[][] BOARD;
        BOARD = new CELL[3][3];

        // define the board elements as EMPTY.
        BOARD [0][0] = CELL.EMPTY; BOARD [0][1] = CELL.EMPTY; BOARD [0][2] = CELL.EMPTY;
        BOARD [1][0] = CELL.EMPTY; BOARD [1][1] = CELL.EMPTY; BOARD [1][2] = CELL.EMPTY;
        BOARD [2][0] = CELL.EMPTY; BOARD [2][1] = CELL.EMPTY; BOARD [2][2] = CELL.EMPTY;

        // create a new object of PLAYERS list, size of 2, giving 0 to p1 and 1 to p2.
        ROUND = CELL.X;
        MOVECOUNT = 0;
        PLAYERS = new String[2];
        PLAYERS[0] = PLAYER1;
        PLAYERS[1] = PLAYER2;
    }

    public enum CELL {
        X, O, EMPTY
    }

    //----------------------------------------- LOGIC-STATUS------------------------------

    public enum GAMESTATUS {
        PLAYER1WINS, PLAYER2WINS, DRAW, CONTINUE
    }

    private static boolean MATCHORNOT(CELL[] KEYWORD){
        CELL CHECK = KEYWORD[0];
        for (int i= 1; i<KEYWORD.length; i++)
            if (CHECK !=KEYWORD[i])
                return false;
        return true;
    }

    public GAMESTATUS RESULT (CELL c) {
        if (c == CELL.X)
            return GAMESTATUS.PLAYER1WINS;
        else if (c == CELL.O)
            return GAMESTATUS.PLAYER2WINS;
        else
            return GAMESTATUS.CONTINUE;
    }

    public GAMESTATUS WINCONDITIONS() {
        for (int i = 0; i <3; i++)
            if (MATCHORNOT(BOARD[i]))
                return RESULT(BOARD[i][0]);  //horizontal
        for (int i = 0; i <3; i++) {
            if ((BOARD[0][i] == BOARD[1][i] && BOARD[1][i] == BOARD[2][i]))
                return RESULT(BOARD[0][0]);

            if ((BOARD[0][0] == BOARD[1][1] && BOARD[1][1] == BOARD[2][2]))
                return RESULT(BOARD[0][0]);

            if ((BOARD[2][0] == BOARD[1][1] && BOARD[1][1] == BOARD[0][2]))
                return RESULT(BOARD[2][0]);
        }
    if (MOVECOUNT ==9)
        return GAMESTATUS.DRAW;

    return GAMESTATUS.CONTINUE;

    }

    //----------------------------------------- LOGIC-MOVE------------------------------

    int MOVECOUNT;

    private CELL ROUND;

    public void NEXT() {
        if (ROUND == CELL.X)
            ROUND = CELL.O;
        else
            ROUND = CELL.X;
    }

    public boolean CHECKPLAYERMOVE (int ROW, int COL ) {
        if ((ROW < 0) || (COL < 0))
            return false;
        if ((ROW > 2) || (COL > 2))
            return false;
        return true;
    }

    public boolean MOVEONBOARD (int ROW, int COL) {
        if ((BOARD[ROW][COL] == CELL.EMPTY) && CHECKPLAYERMOVE(ROW, COL)) {
        BOARD[ROW][COL] = ROUND;
        MOVECOUNT++;
        return true;
        }
        else
        return false;
    }

    //----------------------------------------- BOARD-------------------------------

    public char GETCHAR(CELL POSITION) {
        if (POSITION == CELL.X)
            return 'X';
        else if (POSITION == CELL.O)
            return 'O';
        else
            return ' ';
    }

    public void PRINTBOARD() {
        for (int i = 0; i < 3; i++) {
            System.out.println("———————");
            System.out.print('|');

            for (int j = 0; j < 3; j++)
                System.out.print(GETCHAR(BOARD[i][j]) + "|");
                System.out.println();
            }
        System.out.println("———————");
    }

    private CELL[][] BOARD;

    //----------------------------------------- PLAYER-------------------------------

    public String[] PLAYERS;

    public String PLAYERTOKEN() {
        if (ROUND == CELL.X)
            return PLAYERS[0];
        return PLAYERS[1];
    }

    public String WINNER(GAMESTATUS s) {
        if (s == GAMESTATUS.PLAYER1WINS)
            return PLAYERS[0];
        else
            return PLAYERS[1];
    }
}







