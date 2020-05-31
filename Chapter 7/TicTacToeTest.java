// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Communit College
// Course: CSCI-2251, Spring 2020
// Author: Dr. Chu J. Jong
// 02/06/2020

public class TicTacToeTest
{
   public static void main(String[] args)
   {
      TicTacToe game = new TicTacToe();    // instantiate new TicTacToe object
      game.printBoard();                   // Print the game board on the screen
      game.play();                         // Play the game
   } 
} // end class TicTacToeTest