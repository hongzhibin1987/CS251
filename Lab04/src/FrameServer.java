import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class FrameServer extends JFrame {

    private JPanel serverPanel;
    private JTextField portInput;
    private JTextArea textArea;
    ServerSocket server = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameServer frame = new FrameServer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrameServer() {
        setTitle("Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 275, 560);
        serverPanel = new JPanel();
        serverPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(serverPanel);
        serverPanel.setLayout(null);

        JLabel lblInfo = new JLabel("Port Information");
        lblInfo.setBounds(10, 10, 200, 20);
        serverPanel.add(lblInfo);

        JLabel lblPort = new JLabel("Port:");
        lblPort.setBounds(10, 70, 100, 20);
        serverPanel.add(lblPort);

        portInput = new JTextField();
        portInput.setText("8890");
        portInput.setBounds(80, 70, 170, 20);
        serverPanel.add(portInput);
        portInput.setColumns(5);

        JButton btnStart = new JButton("Start Svcs");
        btnStart.setBounds(10, 100, 100, 20);
        serverPanel.add(btnStart);

        JButton btnClean = new JButton("Clean Text");
        btnClean.setBounds(10, 130, 100, 20);
        serverPanel.add(btnClean);

        JButton btnQuit = new JButton("Stop Svcs");
        btnQuit.setBounds(150, 100, 100, 20);
        serverPanel.add(btnQuit);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(150, 130, 100, 20);
        serverPanel.add(btnExit);

        textArea = new JTextArea();
        textArea.setBounds(10, 170, 240, 300);
        serverPanel.add(textArea);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int port = Integer.parseInt(portInput.getText().trim());
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    System.out.println(ip);
                    server = new ServerSocket(port);
                    textArea.append("Server is running\r\n");
                    new ServerListener().start();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (server != null)
                        server.close();
                    textArea.append("Server is stopped! \r\n");
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        });


        btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                textArea.setText("");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    class ServerListener extends Thread {
        public void run() {
            try {
                while (true) {
                    Socket socket = server.accept();
                    textArea.append("Client is using \n" + socket + "\r\n");
                    SocketThd thd = new SocketThd(socket);
                    thd.start();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    class SocketThd extends Thread {
        Socket socket;
        BufferedReader br = null;
        PrintWriter pw = null;

        public SocketThd(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                pw = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")));

                // read the long string of S from client end
                String S = br.readLine();

                // following is basic convert from string to int-arraylist.
                String[] Matrix = S.split(" ");
                String Row = Matrix[0];
                String Col = Matrix[1];
                String[]  justMatrix = Arrays.copyOfRange(Matrix, 2, Matrix.length);

                int ServerRow = Integer.parseInt(Row);
                int ServerCol = Integer.parseInt(Col);
                int size = justMatrix.length;
                int[] intjustMatrix = new int[size];

                for (int i=0; i<size; i++){
                    intjustMatrix[i] = Integer.parseInt(justMatrix[i]);
                }

                int[] idkMatrix = Arrays.copyOfRange(intjustMatrix, 0, intjustMatrix.length);
                int[][] ServerMatrix = new int[ServerRow*2][ServerCol];

                for (int i= 0; i<ServerRow*2;i++){
                    for (int j = 0; j<ServerCol; j++){
                        ServerMatrix[i][j] = idkMatrix[j%(ServerRow*2) + i*ServerCol];
                    }
                }

                // split the origin 2d arraylist into 2. code is bit different than the origin lab3 one.
                int arrayLength = ServerMatrix.length;
                int halfWayPoint = (int) Math.floor(arrayLength/2);
                int numberofelementsInArray = ServerMatrix[0].length;

                int [][] A = new int[halfWayPoint][numberofelementsInArray];
                for (int i =0; i<halfWayPoint; i++) {
                    A[i] = ServerMatrix[i];
                }

                System.out.println("\nMatrix A:");
                for (int i = 0; i<halfWayPoint; i++){
                    for (int j = 0; j<numberofelementsInArray;j++)
                        System.out.print(A[i][j] + " ");
                        System.out.print("\n");
                }

                int newArrayBLength = ServerMatrix.length - halfWayPoint;
                int [][]B = new int[newArrayBLength][numberofelementsInArray];
                for (int i = halfWayPoint; i<ServerMatrix.length; i++){
                    B[i-halfWayPoint] = ServerMatrix[i];
                }

                System.out.println("\nMatrix B:");
                for (int i = 0; i<newArrayBLength; i++){
                    for (int j = 0; j<numberofelementsInArray;j++)
                        System.out.print(B[i][j] + " ");
                    System.out.print("\n");
                }

                // determine whether the row and col is odd/even. if odd, add modifier.
                int rowmod = 0; int colmod = 0;
                if (ServerRow %2 !=0) {
                    rowmod =1;
                }
                if (ServerCol %2 !=0) {
                    colmod =1;
                }

                // initiate new # or col and row
                int newRow = ServerRow / 2 + rowmod;
                int newCol = ServerCol / 2 + colmod;

                //System.out.print(newRow +"\n");
                //System.out.print(newCol +"\n");

                // break arraylist into 4. a total of 4*3+3 = 15 arraylists.
                int[][] A00 = new int[newRow][newCol];
                int[][] A01 = new int[newRow][newCol];
                int[][] A10 = new int[newRow][newCol];
                int[][] A11 = new int[newRow][newCol];
                int[][] B00 = new int[newRow][newCol];
                int[][] B01 = new int[newRow][newCol];
                int[][] B10 = new int[newRow][newCol];
                int[][] B11 = new int[newRow][newCol];
                int[][] C00;
                int[][] C01;
                int[][] C10;
                int[][] C11;

                // old class import
                class MatrixPrint {
                    public MatrixPrint(int[][] array) {
                        for (int[] row : array) {
                            for (int x : row)
                                System.out.print(x + " ");
                            System.out.println();
                        }
                        System.out.println();
                    }
                }

                // get data into matrix
                for (int r = 0; r < newRow; r++) {
                    for (int c = 0; c < newCol; c++){
                        A00[r][c] = A[r][c];
                        A01[r][c] = A[r][c+newCol-colmod];
                        //new MatrixPrint(A01);
                        A10[r][c] = A[r+newRow-rowmod][c];
                        A11[r][c] = A[r+newRow-rowmod][c+newCol-colmod];

                        B00[r][c] = B[r][c];
                        B01[r][c] = B[r][c+newCol-colmod];
                        B10[r][c] = B[r+newRow-rowmod][c];
                        B11[r][c] = B[r+newRow-rowmod][c+newCol-colmod];
                    }
                }

                // Matrix calculation
                class MatrixCalc extends Thread {
                    private int[][] A;
                    private int[][] B;
                    int[][] C;

                    public MatrixCalc(int[][]A, int[][]B){
                        this.A = A;
                        this.B = B;
                        C = new int[A.length][A[0].length];
                    }
                    @Override
                    public void run(){
                        for (int r = 0; r<A.length; r++){
                            for (int c = 0; c<A[r].length; c++) {
                                C[r][c] = A[r][c] + B[r][c];
                            }
                        }
                    }
                    public int[][] getResult(){
                        return C;
                    }
                }

                // Matrix merge.
                class MatrixMerge {
                    public MatrixMerge(int[][]C00, int[][]C01, int[][]C10, int[][]C11, int newRow, int newCol, int rowmod, int colmod){
                        int m = C00.length;
                        int n = C01.length;
                        int p = C00[0].length;
                        int q = C01[0].length;
                        int[][]C0001 = new int[m][p+q];
                        for (int i=0; i<m; i++){
                            for(int j = 0; j<p+q; j++)
                            {
                                if (j<p){
                                    C0001[i][j] = C00[i][j];
                                }
                                else
                                {
                                    C0001[i][j] = C01[i][j-p];
                                }
                            }
                        }

                        int a = C10.length;
                        int b = C11.length;
                        int c = C10[0].length;
                        int d = C11[0].length;
                        int[][]C1011 = new int[m][p+q];
                        for (int i=0; i<a; i++){
                            for(int j = 0; j<c+d; j++)
                            {
                                if (j<c){
                                    C1011[i][j] = C10[i][j];
                                }
                                else
                                {
                                    C1011[i][j] = C11[i][j-c];
                                }
                            }
                        }

                        int[][] C = new int[m+n-rowmod][p+q-colmod];
                        for (int i = 0; i<newRow; i++){
                            for (int j = 0; j<newCol; j++){
                                C[i][j] = C00[i][j];
                                C[i][j+newCol-colmod] = C01[i][j];
                                C[i+newRow-rowmod][j] = C10[i][j];
                                C[i+newRow-rowmod][j+newCol-colmod] = C11[i][j];
                            }
                        }
                        System.out.println("\nMatrix A + Matrix B");
                        new MatrixPrint(C);

                        System.out.println("\nThis is the Matrix on server after calculation:");
                        StringBuilder sb1 = new StringBuilder();
                        for (int i = 0; i< m+n-rowmod; i++){
                            for (int j = 0; j<p+q-colmod; j++){
                                System.out.print(C[i][j]+" ");
                                sb1.append(String.valueOf(C[i][j]));
                                sb1.append(" ");
                            }
                            sb1.append("\n");
                            System.out.println();
                        }
                        textArea.append("This is the Matrix on server after calculation: \n");
                        textArea.append(sb1.toString());

                        String rtn = sb1.toString();
                        pw.println(rtn);
                        pw.flush();

                    }
                }

                // calculate
                MatrixCalc AB00 = new MatrixCalc(A00, B00);
                MatrixCalc AB01 = new MatrixCalc(A01, B01);
                MatrixCalc AB10 = new MatrixCalc(A10, B10);
                MatrixCalc AB11 = new MatrixCalc(A11, B11);

                // start process
                AB00.start();
                AB01.start();
                AB10.start();
                AB11.start();

                // depends on the timing, join calculation
                AB00.join();
                AB01.join();
                AB10.join();
                AB11.join();

                // get the final result
                C00=AB00.getResult();
                C01=AB01.getResult();
                C10=AB10.getResult();
                C11=AB11.getResult();

                // final matrix merge.
                new MatrixMerge(C00, C01, C10, C11, newRow, newCol, rowmod, colmod);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // TODO: handle finally clause
                try {
                    if (br != null) br.close();
                    if (pw != null) pw.close();
                    if (socket != null) socket.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        }
    }
}