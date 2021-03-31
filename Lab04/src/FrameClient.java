import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class FrameClient extends JFrame {

    private JPanel clientPanel;
    private JTextField portInput;
    private JTextField ipInput;
    private JTextField pathInput;
    private JTextArea displayArea;

    ArrayList C = new ArrayList();

    SocketThd thd = null;
    Socket socket = null;
    BufferedReader br = null;
    PrintWriter pw = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameClient frame = new FrameClient();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrameClient(){
        setTitle("Matrix Calculation (Client)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,275,560);
        clientPanel = new JPanel();
        //clientPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(clientPanel);
        clientPanel.setLayout(null);

        JLabel lblInfo = new JLabel("CONNECT SERVER");
        lblInfo.setBounds(10, 10, 200, 20);
        clientPanel.add(lblInfo);

        JLabel lblIP = new JLabel("IP Address: ");
        lblIP.setBounds(10,40,200,20);
        clientPanel.add(lblIP);

        ipInput = new JTextField();
        ipInput.setText("127.0.0.1");
        ipInput.setBounds(80,40,170,20);
        clientPanel.add(ipInput);
        ipInput.setColumns(18);

        JLabel lblPort = new JLabel("Port:");
        lblPort.setBounds(10,70,100,20);
        clientPanel.add(lblPort);

        portInput = new JTextField();
        portInput.setText("8890");
        portInput.setBounds(80,70,170,20);
        clientPanel.add(portInput);
        portInput.setColumns(5);

        JButton btnStart = new JButton("Connect");
        btnStart.setBounds(10,100,100,20);
        clientPanel.add(btnStart);

        JButton btnQuit = new JButton("Disconnect");
        btnQuit.setBounds(150,100,100,20);
        clientPanel.add(btnQuit);

        JLabel lblUserinput = new JLabel("SELECT MATRIX");
        lblUserinput.setBounds(10, 150, 200, 20);
        clientPanel.add(lblUserinput);

        JLabel lblpath = new JLabel("File Name:");
        lblpath.setBounds(10, 180, 200, 20);
        clientPanel.add(lblpath);

        pathInput = new JTextField();
        pathInput.setText("Matrix.txt");
        pathInput.setBounds(80,180,170,20);
        clientPanel.add(pathInput);
        pathInput.setColumns(20);

        JButton btnDisplay = new JButton("Display");
        btnDisplay.setBounds(10,210,100,20);
        clientPanel.add(btnDisplay);

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(150,210,100,20);
        clientPanel.add(btnCalculate);

        JButton btnClose = new JButton("Quit");
        btnClose.setBounds(150,240,100,20);
        clientPanel.add(btnClose);

        JButton btnClean = new JButton("Clean Text");
        btnClean.setBounds(10,240,100,20);
        clientPanel.add(btnClean);

        JTextArea displayArea = new JTextArea("");
        displayArea.setBounds(10,270,240,240);
        clientPanel.add(displayArea);

        btnStart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String ip = ipInput.getText().trim();
                int port = Integer.parseInt(portInput.getText().trim());

                try {
                    socket = new Socket(ip, port);
                    thd = new SocketThd(socket);
                    thd.start();
                } catch (Exception e2) {
                    //TODO: handle exception
                }
            }
        });

        btnQuit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    pw = new PrintWriter(
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")));
                    br.close();
                    pw.close();
                    socket.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        });

        btnDisplay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String filename = pathInput.getText();
                FileReader reader = null;

                try {
                    reader = new FileReader(filename);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try {
                    displayArea.read(reader,filename);
                    if (filename.equals("Matrix.txt")){
                        Scanner s = new Scanner(displayArea.getText());
                        while (s.hasNext()){
                            C.add(s.nextLine());
                        }
                        displayArea.append("\n ...Import Complete");
                    }
                    else {
                        displayArea.append("File does not exist, please try again");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //System.out.print(C);

                // convert arraylist to string
                StringBuffer sb = new StringBuffer();
                for (Object s: C){
                    sb.append(s);
                    sb.append(" ");
                }
                String a = sb.toString();
                thd.send(a);
                System.out.println(a);
            }
        });

        btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                displayArea.setText("");
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
                // send content from client
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")));
                // receive content from server
                String result = "";
                // pass content from buffered Reader to display Area.

                while ((result = br.readLine()) != null) {
                    displayArea.setText(result);
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // TODO: handle finally clause
                try {
                    if (br != null)
                        br.close();
                    if (pw != null)
                        pw.close();
                    if (socket != null)
                        socket.close();
                } catch (Exception e) {
                    // TODO: handle exception;
                }
            }
        }
        // send raw data
        public void send (String C){
            pw.println(C);
            pw.flush();
        }
    }

}