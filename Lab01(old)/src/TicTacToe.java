import java.util.Scanner;


public class TicTacToe {

    private cell[][] board;
    private int step = 0;
    private boolean xTurn = true;


    //public static void main(String[] args)
    //{
       //TicTacToe game = new TicTacToe();    // instantiate new TicTacToe object
        //game.printBoard();                   // Print the game board on the screen
        //game.play();                         // Play the game
    //}

    public TicTacToe() {
        board = new cell[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = cell.EMPTY;
            }
        }
    }

    enum cell {
        X, O, EMPTY;
    }

    enum decision {
        WIN,DRAW,CONTINUE;
    }

    public void printBoard() {
        System.out.print("-------------\n| ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch(board[i][j]) {
                    case X:
                        System.out.print("X | ");
                        break;
                    case O:
                        System.out.print("Y | ");
                        break;
                    case EMPTY:
                        System.out.print("  | ");
                }
            }
            if ( i < 2) {
                System.out.print("\n-------------\n| ");
            } else {
                System.out.print("\n-------------\n");
            }
        }

    }

    public void printStatus() {
        if(gameStatus() == decision.CONTINUE) {
            System.out.print("Continuing\n");
        } else if (gameStatus() == decision.WIN){
            if(xTurn == false) {
                System.out.print("Play 1 win\n");
            } else {
                System.out.print("Play 2 win\n");
            }
        } else {
            System.out.print("DRAW\n");
        }
    }

    public decision gameStatus() {
        if (step == 9) {
            return decision.DRAW;
        }
        if(board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0] != cell.EMPTY) {
            return decision.WIN;
        } else if(board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2] != cell.EMPTY) {
            return decision.WIN;
        }
        for (int i = 0; i < 3; i++) {
            if(board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && board[i][0] != cell.EMPTY) {
                return decision.WIN;
            } else if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && board[0][i] != cell.EMPTY) {
                return decision.WIN;
            }

        }
        return decision.CONTINUE;
    }


    public boolean vaildMove(int x, int y) {
        if(x<1||x>3||y<1||y>3) {
            System.out.println("Out of range.");
            return false;
        }else if (board[x-1][y-1] != cell.EMPTY) {
            System.out.println( x + "," + y + " already taken.");
            return false;
        }
        return true;
    }

    public void play() {
        int x,y;
        Scanner scan = new Scanner(System.in);
        while(gameStatus() == decision.CONTINUE) {
            printStatus();
            if (xTurn) {
                System.out.print("player 1 Enter row : ");
                x = scan.nextInt();
                System.out.print("player 1 Enter column : ");
                y = scan.nextInt();

                while (!vaildMove(x, y)) {
                    System.out.print("PLEASE player 1 Enter row : ");
                    x = scan.nextInt();
                    System.out.print("PLEASE player 1 Enter column : ");
                    y = scan.nextInt();
                }
                board[x-1][y-1] = cell.X;
                step++;
                xTurn = false;
            } else {
                System.out.print("player 2 Enter row : ");
                x = scan.nextInt();
                System.out.print("player 2 Enter column : ");
                y = scan.nextInt();

                while (!vaildMove(x, y)) {
                    System.out.print("PLEASE player 2 Enter row : ");
                    x = scan.nextInt();
                    System.out.print("PLEASE player 2 Enter column : ");
                    y = scan.nextInt();
                }
                board[x-1][y-1] = cell.O;
                step++;
                xTurn = true;
            }
            printBoard();
        }
        printStatus();
    }

}