package server;

/**
 *
 * @author pande
 */
public class ServerMain {
    public static void main(String[] args) {
        Server s = new Server();
        s.waitingForClient();
    }

}