package server;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private final int serverPort = 1234;
    private ServerSocket server;
    private Socket client;
    private InputStream is;
    private OutputStream os;

    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer();
        String input = tcpServer.read(1);
        System.out.println("read: " + input);
        tcpServer.write("hello, client!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}

    }

    public TCPServer() {
        try {
            server = new ServerSocket(serverPort);
            run();
        } catch (IOException e) {
            System.err.println("Couldn't create server!");
            return;
        }
    }

    private void run() throws IOException {
        client = server.accept();
        is = client.getInputStream();
        os = client.getOutputStream();
    }

    public void write(String message) {
        try {
            os.write(message.getBytes());
        } catch (IOException e) {
            System.err.println("Couldn't write to client!");
        }
    }

    public String read() {
        String input;
        byte[] buffer = new byte[100];
        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couln't read from InputStream!");
            return null;
        }

        input = new String(buffer);
        return input;
    }

    public String read(int length) {
        String input;
        byte[] buffer = new byte[length];
        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couln't read from InputStream!");
            return null;
        }

        input = new String(buffer);
        return input;
    }

    public int getServerPort() {
        return serverPort;
    }
}
