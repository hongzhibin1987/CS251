
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.border.TitledBorder;

public class FrameServer extends JFrame {

    private JPanel contentPane;
    private JTextField textPort;
    private JTextArea textArea;
    ServerSocket server = null;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public FrameServer() {
        setTitle("服务端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 493, 360);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("配置信息");
        label.setBounds(10, 10, 54, 15);
        contentPane.add(label);

        JLabel label_1 = new JLabel("端口");
        label_1.setBounds(10, 35, 36, 15);
        contentPane.add(label_1);

        textPort = new JTextField();
        textPort.setBounds(48, 35, 66, 21);
        textPort.setText("8890");
        contentPane.add(textPort);
        textPort.setColumns(10);

        JButton btnStart = new JButton("开始服务");
        btnStart.setBounds(147, 34, 93, 23);
        contentPane.add(btnStart);

        JButton btnClose = new JButton("停止服务");
        btnClose.setBounds(278, 31, 93, 23);
        contentPane.add(btnClose);

        JPanel panel = new JPanel();
        panel.setBounds(0, 10, 399, 63);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "\u804A\u5929\u8BB0\u5F55", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 83, 457, 228);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 21, 437, 176);
        panel_1.add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);


        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //开始监听
                int port = Integer.parseInt(textPort.getText().trim());
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    System.out.println(ip);
                    server = new ServerSocket(port);//创建ServerSocket对象
                    textArea.append("服务器已开启。\r\n");
                    new ServerListener().start();//开启线程进行客户端连接的监听

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //关闭服务
                try {
                    if (server != null)
                        server.close();
                    textArea.append("服务器已停止！\r\n");
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        });
    }

    //监听客户端连接的线程类
    class ServerListener extends Thread {
        public void run() {
            try {
                while (true) {
                    Socket socket = server.accept();
                    //监听客户端连接，客户端连接成功后，实例化Socket对象
                    //System.out.println("客户端信息："+socket);
                    textArea.append("客户端信息：" + socket + "\r\n");
                    //System.out.println("客户端信息2："+socket);
                    //数据交互
                    //读取数据用
                    SocketThd thd = new SocketThd(socket);
                    thd.start();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    //Socket通讯线程类
    class SocketThd extends Thread {
        Socket socket;//和客户端通讯的Socket对象
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
                String str = "";
                String rtn = "";
                while ((str = br.readLine()) != null) {//接收客户端发送来的信息
                    textArea.append("地址为" + socket.getLocalAddress() +
                            "端口号为" + socket.getPort() + "客户端： " + str + "\r\n");
                    String buff[] = str.split("");
                    String str1 = "";
                    String str2 = "";
                    String opt = "";
                    for (int i = 0; i < buff.length; i++) {
                        if (buff[i].equals("+") || buff[i].equals("-") ||
                                buff[i].equals("*") || buff[i].equals("/")) {
                            opt = buff[i];
                            for (int j = 0; j < i; j++) {
                                str1 = str1 + buff[j];
                            }
                            for (int j = i + 1; j < buff.length; j++) {
                                str2 = str2 + buff[j];
                            }
                            break;
                        }
                    }
                    BigDecimal num1 = new BigDecimal(str1).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal num2 = new BigDecimal(str2).setScale(2, BigDecimal.ROUND_HALF_UP);

                    switch (opt) {
                        case ("+"):
                            rtn = num1.add(num2) + "";
                            break;
                        case ("-"):
                            rtn = num1.subtract(num2) + "";
                            break;
                        case ("*"):
                            rtn = num1.multiply(num2) + "";
                            break;
                        case ("/"):
                            rtn = num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP) + "";
                            break;
                    }


                    //处理结果返回给客户端
                    pw.println(rtn);
                    pw.flush();
                }
                textArea.append("客户端已断开连接！" + "\r\n");
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
