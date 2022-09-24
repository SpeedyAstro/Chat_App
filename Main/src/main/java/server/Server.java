package server;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    //---------------------------THREAD CREATED----------------------------
        Thread thread = new Thread(){
            public void run(){
                while (true){
                    readMessage();
                }
            }
    };
    //---------------------------------------------------------------------
    Server(){
        server_frame = new JFrame("server");
        server_frame.setSize(500,500);
        server_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\pande\\Documents\\ChattingApp\\Main\\src\\main\\resources\\charApp.png");
        server_frame.setIconImage(icon);
        textArea = new JTextArea();
        textArea.setEditable(false);
        Font font = new Font("Arial", Font.BOLD,16);
        textArea.setFont(font);
        textArea.setForeground(Color.BLACK);
        scrollPane = new JScrollPane(textArea);
        server_frame.add(scrollPane); // Scroll pane added on textarea
        textField = new JTextField();
        textField.setEditable(false);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                textArea.append(textField.getText()+"\n"); // working
//                textField.setText("");
                textArea.setForeground(Color.BLACK);
                SendMessage(textField.getText());
                textArea.append("You : "+textField.getText()+"\n");
                textField.setText("");
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

            socket = serverSocket.accept();
            textArea.setText("Client Connected!\n");
            textArea.append("---------------------------------------------\n");
            textArea.setForeground(Color.GREEN);
            textField.setEditable(true);

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
        textArea.append("Client : "+message+"\n");
        chatSound();
    }
    public void SendMessage(String message){
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void readMessage(){
        try {
            String str = dis.readUTF();
            showMessage(str);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    void setIoStream(){
        //textArea.setForeground(Color.BLACK);
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

        }catch (Exception e){
            System.out.println(e);
        }
        thread.start();
    }
    public void chatSound(){
        try {
            String sound = "Main\\src\\main\\java\\server\\alertsound\\pop.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}