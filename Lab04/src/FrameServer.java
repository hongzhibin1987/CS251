import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.ArrayList;
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

        JButton btnQuit = new JButton("Stop Svcs");
        btnQuit.setBounds(150, 100, 100, 20);
        serverPanel.add(btnQuit);

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

                String S = br.readLine();
                System.out.println(S);

                String Matrix[] = S.split(" ");
                String Row = Matrix[0];
                String Col = Matrix[1];
                String[]  justMatrix = Arrays.copyOfRange(Matrix, 2, Matrix.length);

                Integer ServerRow = Integer.valueOf(Row);
                Integer ServerCol = Integer.valueOf(Col);

                System.out.println(ServerRow);
                System.out.println(ServerCol);
                textArea.append("Row of the matrix is "+ ServerRow + "\nCol of the matrix is " + ServerCol + "\n" + "Matrix is \n" );

                int size = justMatrix.length;
                int[] intjustMatrix = new int[size];
                for (int i=0; i<size; i++){
                    intjustMatrix[i] = Integer.parseInt(justMatrix[i]);
                }
                System.out.println(Arrays.toString(intjustMatrix));
                int[] idkMatrix = Arrays.copyOfRange(intjustMatrix, 0, intjustMatrix.length);

                int ServerMatrix[][] = new int[ServerRow*2][ServerCol];

                for (int i= 0; i<ServerRow*2;i++){
                    for (int j = 0; j<ServerCol; j++){
                        ServerMatrix[i][j] = idkMatrix[j%(ServerRow*2) + i*ServerCol];
                    }
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i< ServerRow*2; i++){
                    for (int j = 0; j<ServerCol; j++){
                        System.out.print(ServerMatrix[i][j]);
                        sb.append(String.valueOf(ServerMatrix[i][j]));
                        sb.append(" ");
                      }
                    sb.append("\n");
                    System.out.println();
                }
                textArea.append(sb.toString());

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