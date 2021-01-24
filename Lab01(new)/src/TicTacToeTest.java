// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Communit College
// Course: CSCI-2251, Spring 2021
// Author: Zhibin "Bing" Hong
// 01/14/2021
import java.util.Scanner;
public class TicTacToeTest
{
   public static void main(String[] args) {
      Scanner userInput = new Scanner(System.in);

      String playerO = "Player O";
      String playerX = "Player X";

      TicTacToe game = new TicTacToe(playerO, playerX);

      game.printBoard();

      while (game.moveCounter < 9) {
         game.play();
         game.printBoard();
         game.checkWinner();
      }
      TicTacToe.gameStatus win = game.printWinner();
   } 
} // end class TicTacToeTest