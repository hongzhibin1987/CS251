// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Communit College
// Course: CSCI-2251, Summer 2020
// Author: Zhibin "Bing" Hong
// 06-01-2020

public class TicTacToe
{
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

        


        // TicTacToe game = new TicTacToe();    // instantiate new TicTacToe object
        //  game.play();                         // Play the game
    }
    public static void printBoard(String[][] GameBoard){
        for (String[] row: GameBoard){
            for (String item: row){
                System.out.print(item);
            }
            System.out.println();
        }
    }
}