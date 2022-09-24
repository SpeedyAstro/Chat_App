package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private JFrame server_frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField textField;
    private ServerSocket serverSocket;
    private InetAddress inetAddress;
    Server(){
        server_frame = new JFrame("server");
        server_frame.setSize(500,500);
        server_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        server_frame.add(scrollPane); // Scroll pane added on textarea
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                textArea.append(textField.getText()+"\n"); // working
//                textField.setText("");
                showMessage(e.getActionCommand());
            }
        });
        server_frame.add(textField, BorderLayout.SOUTH);
        server_frame.setVisible(true);
    }
    public void waitingForClient() {
        try {
            String ip = getIpAddress();
            System.out.println(ip);
            serverSocket = new ServerSocket(1111);
            textArea.setText("Server IP : "+ip+"\n Waiting for Client...");

            Socket socket = serverSocket.accept();
            textArea.setText("Client Connected!\n");
            textArea.append("\t\t---------------------------------------------\n");

        } catch (Exception e) {System.out.println(e);}
    }
    public String getIpAddress(){
        try{
            inetAddress = InetAddress.getLocalHost();
             // return ip
        }catch (Exception e){
            System.out.println(e);}
        return inetAddress.getHostAddress();
    }
    public void showMessage(String message){
        textArea.append(message+"\n");
        textField.setText("");
    }

}