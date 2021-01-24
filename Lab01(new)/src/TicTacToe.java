import java.util.Scanner;
public class TicTacToe {


    public enum Cell{
        O, X, EMPTY
    }

    public enum gameStatus{
        OWIN, XWIN, CONTINUE
    }

    private String[] player;

    private Cell[][] board;

    private Cell move;

    int moveCounter;

    // instantiate with playerO and playerX
    public TicTacToe(String playerO, String playerX) {
        board = new Cell[3][3];
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                board[i][j] = Cell.EMPTY;

        move = Cell.X;
        moveCounter = 0;
        player = new String[2];
        player[0] = playerX;
        player[1] = playerO;
    }

    // method printBoard
    public void printBoard(){
        for (int i = 0; i<3; i++){
            System.out.println("-------");
            System.out.print("|");
            for (int j = 0; j<3; j++)
                System.out.print(getChar(board[i][j]) + "|");
                System.out.println();
            }
        System.out.println("-------");
    }

    // method getChar, turn eunm into char for print purpose
    public char getChar(Cell element){
        if (element == Cell.X)
            return 'X';
        if (element == Cell.O)
            return 'O';
        else
            return ' ';
    }

    public String getPlayer (){
        if (move == Cell.X){
            return player[0];}
            else{
                return player[1];
            }
    }

    // main logic
    public void play(){
        // ask for user input
        Scanner userInput = new Scanner(System.in);
        int row, col;
        System.out.print(getPlayer() + "'s" + " turn. \n");
        System.out.println(getPlayer() + ", Enter row(0, 1, or 2):");
        row = userInput.nextInt();
        // input has to fall in certain range
            while (row<0 || row>2) {
                System.out.println("Please enter a number among 0, 1 and 2!");
                System.out.println(getPlayer() + ", Enter row(0, 1, or 2):");
                row = userInput.nextInt();
            }

        System.out.println(getPlayer() + ", Enter col(0, 1, or 2):");
        col = userInput.nextInt();
            while (col<0 || col>2) {
                System.out.println("Please enter a number among 0, 1 and 2!");
                System.out.println(getPlayer() + ", Enter col(0, 1, or 2):");
                col = userInput.nextInt();
            }

        // check if position is taken

        // change turns
        if (move == Cell.X)
            move = Cell.O;
        else
            move = Cell.X;
        moveCounter++;
    }

    public gameStatus checkWinner(){}

    public String printWinner(gameStatus winner){
        if (winner == gameStatus.XWIN)
            return player[0];
        else
            return player[1];
    }


}
