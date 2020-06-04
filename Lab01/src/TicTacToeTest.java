import java.util.Scanner;

public class TicTacToeTest {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        // define the two items to be passed in TicTacToe;
        String USER1 = "PLAYER 1";
        String USER2 = "PLAYER 2";

        // starting a new game.
        TicTacToe game = new TicTacToe(USER1, USER2);

        // the game main logic.I
        while (game.WINCONDITIONS() == TicTacToe.GAMESTATUS.CONTINUE) {
            // initiate user input and a status check.
            int USERINPUTROW;
            int USERINPUTCOL;
            boolean FINISHED = false;

            do {
                game.PRINTBOARD();

                System.out.print(game.PLAYERTOKEN() + "'s turn. \n");
                System.out.print(game.PLAYERTOKEN() + ", Enter row (0, 1, or 2). \n");
                USERINPUTROW = userInput.nextInt();

                System.out.print(game.PLAYERTOKEN() + ", Enter col (0, 1, or 2). \n");
                USERINPUTCOL = userInput.nextInt();

                if (!game.CHECKPLAYERMOVE(USERINPUTROW, USERINPUTCOL))
                    System.out.print("Sorry, those are invalid entries");
                else{
                    if (!game.MOVEONBOARD(USERINPUTROW, USERINPUTCOL))
                        System.out.println("Sorry, that square is taken");
                    else
                        FINISHED = true;
                }
            }
            while (!FINISHED);
            game.NEXT();
        }

        game.PRINTBOARD();
        TicTacToe.GAMESTATUS RESULT = game.WINCONDITIONS();

        if (RESULT == TicTacToe.GAMESTATUS.DRAW)
            System.out.println("This game is a draw");
        else {
            System.out.print(game.WINNER(RESULT) + " has won the game!");
        }

    }
}


