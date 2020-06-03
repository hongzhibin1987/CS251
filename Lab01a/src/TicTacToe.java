
import java.util.Scanner;
public class TicTacToe {

    //Represents the value of a spot on the board.
    public enum Cell {X, O, EMPTY}

    //Represents the status of game.
    public enum gameStatus {XWINS, OWINS, DRAW, INCOMPLETE}

    //Game board for tictactoe.
    private Cell[][] board;

    //Players will hold player x and player y.
    private String[] players;

    // turn represents which player’s turn it is.
    private Cell turn;

    // numMoves represents the amount of moves made thus far. (9 is max in tictactoe)
    int numMoves;

    //Initializes TicTacToe, which will create an empty board.
    //TicTacToe will also start the numMoves to zero, a fresh game.

    public TicTacToe(String player1, String player2) {

        board = new Cell[3][3];

        //loop through rows and columns to fill board with empty cells/spots.
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Cell.EMPTY; //each spot will be set to the enum EMPTY.

        turn = Cell.X; //The first turn goes to player X.
        numMoves = 0; //The number of moves is set to zero. (a fresh game).
        players = new String[2]; //Game set for two players.
        players[0] = player1;
        players[1] = player2;
    }

    // Prints out the current game board.
    public void printboard() {
        //Loop through the board Cells and print 3 horizontal / vertical lines.
        for (int i = 0; i < 3; i++) {
            System.out.println("——————-");
            System.out.print('|');

            //Print each cell value and add remaining vertical lines.
            //We use the getChar method to convert our enum value to a char so we can print to board.
            for (int j = 0; j < 3; j++)
                System.out.print(getChar(board[i][j]) + "|");
            System.out.println();
        }
        //One final vertical line is added to bottom of board.
        System.out.println("——————-");
    }

    //getChar converts our enum values of a cell to a printable char.
    public char getChar(Cell p) {
        if (p == Cell.X)
            return 'X'; //If the cell is enum X, return char X.
        else if (p == Cell.O)
            return 'O'; //If the cell is enum O, return char O.
        else
            return ' '; //If the cell is empty, return a space.
    }

    //getCurPlayer returns the name of the player in relation to who’s turn it is.
    public String getCurPlayer() {

        if (turn == Cell.X) //If turn is set to enum X, return player X.
            return players[0];
        return players[1];
    }

    // Change turn is used each time a player mades a valid move.
    public void changeTurn() {
        //Swap values of X and O
        if (turn == Cell.X)
            turn = Cell.O;
        else
            turn = Cell.X;
    }

    //Move method uses player’s input to assign valid move to board
    //and increment number of moves by 1.
    public boolean Move(int row, int column) {

        // If a players move is in an empty cell, and within 0-2 for row and columns.
        if ((board[row][column] == Cell.EMPTY) && inbounds(row, column)) {
            board[row][column] = turn; //Assign the move.
            numMoves++; //Add one to the number of moves total.
            return true;
        } else
            return false;
    }

    //inbounds will check the user’s move input and return true if acceptable.
    public boolean inbounds(int row, int column) {
        if ((row < 0) || (column < 0))
            return false;
        if ((row > 2) || (column > 2))
            return false;
        return true;
    }

    // winner will return a status that relates to the winner.
    public gameStatus winner() {

        for (int i = 0; i < 3; i++) //Run through each cell to check value for 3 in-a-row.
            if (SameArray(board[i]))
                return convertStatus(board[i][0]); //If horizontal 3 in-a-row.

//Runs through each cell and if similar, converts the Cell enum value to char of same value.
        for (int i = 0; i < 3; i++) {
            if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) //If vertical 3 in-a-row.
                return convertStatus(board[0][i]);

            if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) //If diagonal ( \ ) 3 in-a-row.
                return convertStatus(board[0][0]);

            if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2])) //If diagonal ( / ) 3 in-a-row.
                return convertStatus(board[2][0]);
        }

        if (numMoves == 9) //If number of moves = 9 , board is full and is a DRAW.
            return gameStatus.DRAW;

        return gameStatus.INCOMPLETE; //If not full, and no winner, game continues.
    }

    //This method will be used in winner to convert the value of a cell to the related gameStatus.
    public gameStatus convertStatus(Cell p) {
        if (p == Cell.X) //If a cell value is enum X, return gameStatus XWINS.
            return gameStatus.XWINS;

        else if (p == Cell.O) //If a cell value is enum O, return gameStatus OWINS.
            return gameStatus.OWINS;

        return gameStatus.INCOMPLETE; //If cell value is EMPTY, return INCOMPLETE.
    }

    //SameArray compares cells of an array and returns true if similar.
    private static boolean SameArray(Cell[] word) {

        Cell check = word[0];
        for (int i = 1; i < word.length; i++)
            if (check != word[i])
                return false; //If cell is not similar to another cell return false.
        return true;
    }

    // whoWon will be used to relate the XWINS or OWINS gameStatus to the correct player.
    public String whoWon(gameStatus s) {
        if (s == gameStatus.XWINS)
            return players[0]; //Return player X if XWINS is the gameStatus.

        else
            return players[1]; //Return player O if XWINS is not the gameStatus.
    }

    //main method. calls to each method to print, read and check input,
    //changes board, and checks for winner each run through of the loop.


}
