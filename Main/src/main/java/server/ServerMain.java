package server;

/**
 *
 * @author pande
 */
public class ServerMain {
    public static void main(String[] args) {
        Server s = new Server(); // Invoke GUI part
        s.waitingForClient(); // it will wait for the client
        s.setIoStream(); // transfer data
    }

}