public class TicTacToe {
    // create enum cell
    public enum Cell {
        O, X, EMPTY
    }

    // create enum winning status
    public enum gameStatus {
        WIN, DRAW, CONTINUE
    }

    private String[] player;

    private Cell[][] board;

    private Cell move;

    int moveCounter;

    // main method, need pass two strings
    public TicTacToe(String playerO, String playerX) {
        board = new Cell[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Cell.EMPTY;

        move = Cell.X;
        moveCounter = 0;
        player = new String[2];
        player[0] = playerX;
        player[1] = playerO;
    }

    // method printBoard
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------");
            System.out.print("|");
            for (int j = 0; j < 3; j++)
                System.out.print(getChar(board[i][j]) + "|");
            System.out.println();
        }
        System.out.println("-------");
    }

    // method getChar, turn eunm into char for print purpose
    public char getChar(Cell element) {
        if (element == Cell.X)
            return 'X';
        if (element == Cell.O)
            return 'O';
        else
            return ' ';
    }

    // determine who is going to make next move. since move is going to swap, player is going to swap.
    public String getPlayer() {
        if (move == Cell.X) {
            return player[0];
        } else {
            return player[1];
        }
    }

    // logic of determine whether player's move is valid. 1) has to be within certain range, 2) has to be empty.
    public boolean validMove(int row, int col) {
        if ((row < 0) || (col < 0) || (row > 2) || (col > 2) || (board[row][col] != Cell.EMPTY))
            return false;
        else
            return true;
    }

    // if move is valie, turn the board[][] into move.
    public boolean setMove(int row, int col) {
        if (validMove(row, col)) {
            board[row][col] = move;
            return true;
        } else
            return false;
    }

    // swap the player/move
    public void swapMove() {
        if (move == Cell.X)
            move = Cell.O;
        else
            move = Cell.X;
        moveCounter++;
    }

    // main logic to decide the game status. win? lose? or draw.
    public gameStatus getStatus() {
        for (int i = 0; i < 3; i++) {
            if (((board[i][0] == board[i][1] && board[i][1] == board[i][2]) && board[i][0] != Cell.EMPTY) ||
                    (((board[0][i] == board[1][i] && board[1][i] == board[2][i]) && board[0][i] != Cell.EMPTY)))
                return gameStatus.WIN;
        }
        if (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != Cell.EMPTY)  ||
                (((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != Cell.EMPTY) ))
            return gameStatus.WIN;
        if (moveCounter == 9)
            return gameStatus.DRAW;
        else
            return gameStatus.CONTINUE;
    }

    // if there is a winner, decide who is the winner. <- this method can be optimized, otherwise it's kind stupid :/
    public void printWinner() {
        for (int i = 0; i < 3; i++) {
            if (((board[i][0] == board[i][1] && board[i][1] == board[i][2]) && board[i][0] != Cell.EMPTY) & board[i][0] == Cell.X ||
                    (((board[0][i] == board[1][i] && board[1][i] == board[2][i]) && board[0][1] != Cell.EMPTY) & board[0][i] == Cell.X ))
                System.out.println("Player X win");
            else if (((board[i][0] == board[i][1] && board[i][1] == board[i][2]) && board[i][0] != Cell.EMPTY) & board[i][0] == Cell.O ||
                    (((board[0][i] == board[1][i] && board[1][i] == board[2][i]) && board[0][1] != Cell.EMPTY) & board[0][i] == Cell.O ))
                System.out.println("Player O win");
        }
        if (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.X ||
                (((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != Cell.EMPTY) & board[2][0] == Cell.X))
            System.out.println("Player X win");
        if (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.O ||
                (((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != Cell.EMPTY) & board[2][0] == Cell.O))
            System.out.println("Player O win");
    }
}




