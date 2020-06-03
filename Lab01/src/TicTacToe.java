// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Communit College
// Course: CSCI-2251, Summer 2020
// Author: Zhibin "Bing" Hong
// 06-01-2020

import java.util.Scanner;
// Initiate public class TTT;
public class TicTacToe {

    // Initiate enum X, O and EMPTY.
    public enum CELL {
        X, O, EMPTY
    }

    // Initiate enum gamestatus
    public enum GAMESTATUS {
        WIN, DRAW, CONTINUE
    }

    // Initiate Players
    public String[] PLAYERS;

    // Initiate Board

    private CELL[][] BOARD;

    public TicTacToe(String PLAYER1, String PLAYER2) {
        BOARD = new CELL[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                BOARD[i][j] = CELL.EMPTY;
        PLAYERS = new String[2];
        PLAYERS[0] = PLAYER1;
        PLAYERS[1] = PLAYER2;
    }

    public char getChar(CELL POSITION) {
        if (POSITION == CELL.X)
            return 'X';
        else if (POSITION == CELL.O)
            return 'O';
        else
            return ' ';
    }

    public void PRINTBOARD() {
        for (int i = 0; i < 3; i++) {
            System.out.println("——————-");
            System.out.print('|');

            for (int j = 0; j < 3; j++) {
                System.out.print(getChar(BOARD[i][j]) + "|");
                System.out.println();
            }

        }
    }

}