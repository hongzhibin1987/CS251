public class TicTacToe {

    public enum Cell {
        O, X, EMPTY
    }

    public enum gameStatus {
        WIN, DRAW, CONTINUE
    }

    private String[] player;

    private Cell[][] board;

    private Cell move;

    int moveCounter;

    // instantiate with playerO and playerX
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

    public String getPlayer() {
        if (move == Cell.X) {
            return player[0];
        } else {
            return player[1];
        }
    }

    /* main logic
    public cell getMove() {
        // ask for user input
        Scanner userInput = new Scanner(System.in);
        int row, col;
        System.out.print(getPlayer() + "'s" + " turn. \n");
        System.out.println(getPlayer() + ", Enter row(0, 1, or 2):");
        row = userInput.nextInt();
        System.out.println(getPlayer() + ", Enter col(0, 1, or 2):");
        col = userInput.nextInt();
    }

     */

    public boolean validMove(int row, int col) {
        if ((row < 0) || (col < 0) || (row > 2) || (col > 2) || (board[row][col] != Cell.EMPTY))
            return false;
        else
            return true;
    }

    public boolean setMove(int row, int col) {
        if (validMove(row, col)) {
            board[row][col] = move;
            moveCounter++;
            return true;
        } else
            return false;
    }

    public void swapMove() {
        if (move == Cell.X)
            move = Cell.O;
        else
            move = Cell.X;
        moveCounter++;
    }

    public gameStatus getStatus() {
        for (int i = 0; i < 3; i++) {
            if (((board[i][0] == board[i][1] && board[i][1] == board[i][2]) && board[i][0] != Cell.EMPTY) & board[i][0] == Cell.X ||
                    (((board[0][i] == board[1][i] && board[1][i] == board[2][i]) && board[0][1] != Cell.EMPTY) & board[0][i] == Cell.X ||
                            (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.X ||
                                    (((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != Cell.EMPTY) & board[2][0] == Cell.X))))
                return gameStatus.WIN;
        }
        if (moveCounter == 9)
            return gameStatus.DRAW;
        else
            return gameStatus.CONTINUE;
    }

    public void printWinner() {
        if (((board[0][0] == board[0][1] && board[0][1] == board[0][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.X ||
                    (((board[0][1] == board[1][1] && board[1][1] == board[2][1]) && board[0][1] != Cell.EMPTY) & board[0][1] == Cell.X ||
                            (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.X ||
                                    (((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != Cell.EMPTY) & board[2][0] == Cell.X))))
            System.out.println("X win");
        else if (((board[0][0] == board[0][1] && board[0][1] == board[0][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.O ||
                (((board[0][1] == board[1][1] && board[1][1] == board[2][1]) && board[0][1] != Cell.EMPTY) & board[0][1] == Cell.O ||
                        (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != Cell.EMPTY) & board[0][0] == Cell.O ||
                                (((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != Cell.EMPTY) & board[2][0] == Cell.O))))
            System.out.println("O win");
        }
    }




