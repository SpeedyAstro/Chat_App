package client;

public class ClientDriver {
    public static void main(String[] args) {
        ClientIF client = new ClientIF();
        client.setIoStream();
    }
}
