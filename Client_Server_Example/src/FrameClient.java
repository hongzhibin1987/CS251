
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FrameClient extends JFrame {

    private JPanel contentPane;
    private JTextField txtPort;
    private JTextField txtIP;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    String num1 = "";
    String num2 = "";
    String opt = "";
    SocketThd thd = null;
    Socket socket = null;
    BufferedReader br = null;
    PrintWriter pw = null;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public FrameClient() {
        setTitle("客户端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(170, 159, 2, 2);
        contentPane.add(scrollPane);

        txtPort = new JTextField();
        txtPort.setText("8890");
        txtPort.setBounds(176, 24, 66, 23);
        contentPane.add(txtPort);
        txtPort.setColumns(10);

        JButton btnStart = new JButton("登录");

        btnStart.setBounds(270, 24, 66, 23);
        contentPane.add(btnStart);

        JButton btnClose = new JButton("退出");

        btnClose.setBounds(358, 24, 66, 23);
        contentPane.add(btnClose);

        JLabel lblNewLabel = new JLabel("登录信息");
        lblNewLabel.setBounds(10, 10, 54, 15);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("端口");
        label.setBounds(143, 28, 29, 15);
        contentPane.add(label);

        txtIP = new JTextField();
        txtIP.setText("127.0.0.1");
        txtIP.setBounds(67, 25, 66, 21);
        contentPane.add(txtIP);
        txtIP.setColumns(10);

        JLabel lblIp = new JLabel("服务器IP");
        lblIp.setBounds(10, 28, 54, 15);
        contentPane.add(lblIp);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 414, 47);
        contentPane.add(panel);

        JLabel label_1 = new JLabel("操作");
        label_1.setBounds(10, 82, 54, 15);
        contentPane.add(label_1);

        JLabel label_2 = new JLabel("操作数1");
        label_2.setBounds(43, 128, 54, 15);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("操作数2");
        label_3.setBounds(43, 163, 54, 15);
        contentPane.add(label_3);

        JLabel label_4 = new JLabel("操作数3");
        label_4.setBounds(43, 198, 54, 15);
        contentPane.add(label_4);

        txt1 = new JTextField();
        txt1.setBounds(107, 125, 135, 21);
        contentPane.add(txt1);
        txt1.setColumns(10);

        txt2 = new JTextField();
        txt2.setColumns(10);
        txt2.setBounds(107, 159, 135, 21);
        contentPane.add(txt2);

        txt3 = new JTextField();
        txt3.setColumns(10);
        txt3.setBounds(107, 195, 135, 21);
        contentPane.add(txt3);

        btnStart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //登录
                String ip = txtIP.getText().trim();//服务器的IP
                int port = Integer.parseInt(txtPort.getText().trim());

                try {
                    socket = new Socket(ip, port);//建立与服务器的连接
                    thd = new SocketThd(socket);
                    thd.start();

                } catch (Exception e2) {
                    // TODO: handle exception
                }

            }
        });

        btnClose.addActionListener(new ActionListener() {
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

        JButton btnPlus = new JButton("加法(+)");
        btnPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = txt1.getText().trim();
                num2 = txt2.getText().trim();
                thd.send(num1, num2, "+");
            }
        });
        btnPlus.setHorizontalAlignment(SwingConstants.LEFT);
        btnPlus.setBounds(252, 128, 81, 35);
        contentPane.add(btnPlus);

        JButton btnSub = new JButton("减法(-)");
        btnSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = txt1.getText().trim();
                num2 = txt2.getText().trim();
                thd.send(num1, num2, "-");

            }
        });
        btnSub.setHorizontalAlignment(SwingConstants.LEFT);
        btnSub.setBounds(343, 126, 82, 35);

        contentPane.add(btnSub);

        JButton btnMui = new JButton("乘法(*)");
        btnMui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = txt1.getText().trim();
                num2 = txt2.getText().trim();
                thd.send(num1, num2, "*");
            }
        });
        btnMui.setHorizontalAlignment(SwingConstants.LEFT);
        btnMui.setBounds(252, 188, 78, 35);
        contentPane.add(btnMui);

        JButton btnDiv = new JButton("除法(/)");
        btnDiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = txt1.getText().trim();
                num2 = txt2.getText().trim();
                thd.send(num1, num2, "/");
            }
        });
        btnDiv.setHorizontalAlignment(SwingConstants.LEFT);
        btnDiv.setBounds(343, 188, 82, 35);
        contentPane.add(btnDiv);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 67, 424, 184);
        contentPane.add(panel_1);


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
                //写入流操作
                pw = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")));
                //接收服务器发送来的信息
                String result = "";
                while ((result = br.readLine()) != null) {
                    txt3.setText(result);
                }
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

        //信息发送
        public void send(String num1, String num2, String opt) {
            pw.println(num1 + opt + num2);
            pw.flush();
        }
    }
}
