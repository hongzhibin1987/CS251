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
      int row, col;
      while (game.moveCounter <9){
         game.printBoard();
         System.out.print(game.getPlayer() + "'s" + " turn. \n");
         System.out.println(game.getPlayer() + ", Enter row(0, 1, or 2):");
         row = userInput.nextInt();
         System.out.println(game.getPlayer() + ", Enter col(0, 1, or 2):");
         col = userInput.nextInt();

         while (game.validMove(row, col) == false) {
            System.out.println("Enter invalid. Please reenter your choice");
            System.out.print(game.getPlayer() + "'s" + " turn. \n");
            System.out.println(game.getPlayer() + ", Enter row(0, 1, or 2):");
            row = userInput.nextInt();
            System.out.println(game.getPlayer() + ", Enter col(0, 1, or 2):");
            col = userInput.nextInt();
         }
         game.setMove(row, col);
         game.swapMove();

         TicTacToe.gameStatus win = game.getStatus();
         if (win == TicTacToe.gameStatus.DRAW) {
            System.out.println("This is a draw");
         }
         else {
            game.printWinner();
         }
      }
      }




      //TicTacToe.gameStatus win = game.getWinner();
      }

// end class TicTacToeTest