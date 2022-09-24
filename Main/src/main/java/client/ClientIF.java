package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientIF {
    private JFrame client_frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField textField;
    private Socket socket;
    String ip;
    private DataOutputStream dos;
    private DataInputStream dis;
    //private ServerSocket serverSocket;
    //---------------------------THREAD CREATED----------------------------
    Thread thread = new Thread(){
        public void run(){
            while (true){
                readMessage();
            }
        }
    };
    //---------------------------------------------------------------------
    ClientIF(){
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
                textField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SendMessage(textField.getText());
                        textArea.append("You : "+textField.getText()+"\n");
                        textField.setText("");
                    }
                });
                client_frame.add(textField, BorderLayout.SOUTH);
                client_frame.setVisible(true);}
        }
    }
    void connectTOServer(){
        try{
            socket = new Socket(ip,1111);

        }catch (Exception e){
            System.out.println(e);
        }
    }
    void setIoStream(){
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

        }catch (Exception e){
            System.out.println(e);
        }
        thread.start();
    }
    public void SendMessage(String message){
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void showMessage(String message){
        textArea.append("Server : "+message+"\n");
    }
    public void readMessage(){
        try {
            String str = dis.readUTF();
            showMessage(str);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

}
