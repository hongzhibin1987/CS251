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

    // create a public constructor to pass PLAYER1 and PLAYER2 in;
    public TicTacToe(String PLAYER1, String PLAYER2) {

        // create a new object from CELL[][] BOARD;
        BOARD = new CELL[3][3];

        // define the board elements as EMPTY.
        BOARD [0][0] = CELL.EMPTY; BOARD [0][1] = CELL.EMPTY; BOARD [0][2] = CELL.EMPTY;
        BOARD [1][0] = CELL.EMPTY; BOARD [1][1] = CELL.EMPTY; BOARD [1][2] = CELL.EMPTY;
        BOARD [2][0] = CELL.EMPTY; BOARD [2][1] = CELL.EMPTY; BOARD [2][2] = CELL.EMPTY;

        // create a new object of PLAYERS list, size of 2, giving 0 to p1 and 1 to p2.
        ROUND = CELL.X;

        // at the beginning, the move count = 0 and start counting;
        MOVECOUNT = 0;

        // from the public string list of PLAYERS, create a new object of player, and give it a size of 2;
        PLAYERS = new String[2];
        PLAYERS[0] = PLAYER1;
        PLAYERS[1] = PLAYER2;
    }

    // create an enum containing the status of each CELL, O, X or EMPTY.
    public enum CELL {
        X, O, EMPTY
    }

    //----------------------------------------- BOARD-------------------------------

    // create object BOARD based on the elements of CELL.
    private CELL[][] BOARD;

    // turn CELL objects into char so it can be printed.
    public char GETCHAR(CELL POSITION) {
        if (POSITION == CELL.X)
            return 'X';
        else if (POSITION == CELL.O)
            return 'O';
        else
            return ' ';
    }

    // print out the board itself.
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

    //----------------------------------------- PLAYER-------------------------------

    // create an object (instance variable-list) of PLAYERS
    public String[] PLAYERS;

    // assian a token to the player. P1= X, P2 = O
    public String PLAYERTOKEN() {
        if (ROUND == CELL.X)
            return PLAYERS[0];
        return PLAYERS[1];
    }

    //----------------------------------------- LOGIC-STATUS------------------------------

    // create an enum containing the status of the game. P1 WIN, P2WIN, DRAW or CONTINUE.
    public enum GAMESTATUS {
        PLAYER1WINS, PLAYER2WINS, DRAW, CONTINUE
    }

    // after GAMESTATUS is evaluated, P1 win = P1win, else P2 win.
    public String WINNER(GAMESTATUS s) {
        if (s == GAMESTATUS.PLAYER1WINS)
            return PLAYERS[0];
        else
            return PLAYERS[1];
    }

    // create a boolean condition. use CELL type, and giving a KEY list.
    private static boolean CHECKCELL (CELL[] KEY){
        // define CHECK as the 0th/1st element of list KEY, from list CELL;
        CELL CHECK = KEY[0];
        // run through the list of KEY.
        for (int i= 1; i<KEY.length; i++)
            // if CHECK does not equal to any element in KEY
            if (CHECK !=KEY[i])
                // return false, so CHECKCELL didn't pass.
                return false;
        return true;
    }

    // main method to check the condition of the winner.
    public GAMESTATUS VICTORY() {
        // run for CHECKCELL(BOARD)[0], CHECKCELL(BOARD)[1] and CHECKCELL(BOARD)[2]
        for (int i = 0; i <3; i++)
            // if CHECKCELL returned match to BOARD[1], [2] and [3]
            if (CHECKCELL(BOARD[i]))
                return RESULT(BOARD[i][0]);  //win as horizontal: [1][0], [2][0], [3][0]
        for (int i = 0; i <3; i++) {
            if ((BOARD[0][i] == BOARD[1][i] && BOARD[1][i] == BOARD[2][i]))
                return RESULT(BOARD[0][i]); //win as vertical: [0][1], [1][1], [2][1]

            if ((BOARD[0][0] == BOARD[1][1] && BOARD[1][1] == BOARD[2][2]))
                return RESULT(BOARD[0][0]); //win as diagnol: [0][0], [1][1], [2][2]

            if ((BOARD[2][0] == BOARD[1][1] && BOARD[1][1] == BOARD[0][2]))
                return RESULT(BOARD[2][0]); //win as \ diagnol: [0][2], [1][1], [2][0]
        }
        if (MOVECOUNT ==9) // otherwise when counter reacher to 9 call it a draw.
            return GAMESTATUS.DRAW;

        return GAMESTATUS.CONTINUE; // if none of above happened, continue
    }

    // pass result from CELL result
    public GAMESTATUS RESULT (CELL RESULT) {
        if (RESULT == CELL.X)
            return GAMESTATUS.PLAYER1WINS;
        else if (RESULT == CELL.O)
            return GAMESTATUS.PLAYER2WINS;
        else
            return GAMESTATUS.CONTINUE;
    }

    //----------------------------------------- LOGIC-MOVE------------------------------

    // to make sure player's move is logical, within 0,1,2.
    public boolean CHECKPLAYERMOVE (int ROW, int COL ) {
        if ((ROW < 0) || (COL < 0))
            return false;
        if ((ROW > 2) || (COL > 2))
            return false;
        return true;
    }

    // CELL element ROUND. to show which palyer's turn.
    private CELL ROUND;

    // used to change who's turn.
    public void NEXT() {
        if (ROUND == CELL.X)
            ROUND = CELL.O;
        else
            ROUND = CELL.X;
    }

    // int movecount. max == 9
    int MOVECOUNT;

    // record player's move, and add move count.
    public boolean MOVEONBOARD (int ROW, int COL) {
        // if the entered position falls on board is EMPTY, and if CHECKPLAYERMOVE returns TRUE:
        if ((BOARD[ROW][COL] == CELL.EMPTY) && CHECKPLAYERMOVE(ROW, COL)) {
            // define ROUND as BOARD[playermove1][player enter 2]
            BOARD[ROW][COL] = ROUND;
            // counter ++
            MOVECOUNT++;
            return true;
            }
            else
            return false;
    }

}