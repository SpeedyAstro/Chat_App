package client;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class Client {
    private JFrame client_frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField textField;
    private Socket socket;
    String ip;
    //private ServerSocket serverSocket;
    Client(){
        ip = JOptionPane.showInputDialog("Enter IP Address to connect ");
        if(ip!=null) {
            if(!ip.equals("")){
                connectTOServer();
                client_frame = new JFrame("Client");
                client_frame.setSize(500, 500);
                client_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                textArea = new JTextArea();
                textArea.setEditable(false);
                scrollPane = new JScrollPane(textArea);
                client_frame.add(scrollPane); // Scroll pane added on textarea
                textField = new JTextField();
                client_frame.add(textField, BorderLayout.SOUTH);
                client_frame.setVisible(true); }
        }
    }
    void connectTOServer(){
        try{
            socket = new Socket(ip,1111);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
