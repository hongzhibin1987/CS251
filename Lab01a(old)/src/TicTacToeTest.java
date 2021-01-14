import java.util.Scanner;

public class TicTacToeTest {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); //Declare userInput scanner to read in moves.

        //Assign player X to user1, and O to user2. Player X always starts first.
        String user1 = "Player X";
        String user2 = "Player O";

        TicTacToe game = new TicTacToe(user1, user2); //Creating game object.

        //While game board is INCOMPLETE (not full / no winner), keep taking moves.
        while (game.winner() == TicTacToe.gameStatus.INCOMPLETE) {
            int r, c; //r, and c relate to player input for rows, and columns, respectively.
            boolean done = false; //Done will be used to check if turn is complete.

            do //Read input from player while done is false.
            {
                game.printboard(); //Prints board to screen.

                System.out.print(game.getCurPlayer() + "‘s turn.\n); ");//Print which players turn it is.
                System.out.println(game.getCurPlayer() + ", Enter row(0, 1, or 2)"); //Asks for row input.
                r = userInput.nextInt(); //Assign row input to r.

                System.out.println(game.getCurPlayer() + ", Enter column(0, 1, or 2)"); //Asks for column input.
                c = userInput.nextInt(); //Assign column input to c.

                //Check to see if row and column are acceptable values using inbounds method.
                if (!game.inbounds(r, c))
                    System.out.println("Sorry, those are invalid entries."); //Alert user bad input.
                else {
                    if (!game.Move(r, c)) //Check to see if desired cell is full.
                        System.out.println("Sorry, that square is taken.");
                    else
                        done = true; //If move passes inbounds and Move test, finish do{}.
                }
            }
            while (!done);

            game.changeTurn(); //Change turn after completing valid input, run loop again until winner or full.
        } //End of core while loop.

        game.printboard(); //After initial while loop finishes, print board one final time.
        TicTacToe.gameStatus win = game.winner(); //Create win to be used as temp to check for draw.

        //If the status of current game is DRAW, alert players.
        if (win == TicTacToe.gameStatus.DRAW)
            System.out.println("The game is a draw!");
            //If not draw, print out the name of the winner and verbaly praise them. 😉
        else {
            System.out.print(game.whoWon(win) + " has won the game!");
        }
    }//End of main
}
