package client;

import server.TCPServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public static void main(String[] args) {
        TCPClient client = new TCPClient();

        if (client.connect("localhost", 1234)) {
            client.send("Hallo");
            String line = client.read();
            System.out.println("read (C): " + line);
        }
    }

    public boolean connect(String hostname, int port) {
        try {
            System.out.println("trying to connect...");
            socket = new Socket(hostname, port);
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            System.err.println("Couldn't connect to " + hostname + " at port " + port);
            return false;
        }

        return true;
    }

    public void send(String message) {
        try {
            os.write(message.getBytes());
        } catch (IOException e) {
            System.err.println("Couldn't send message");
            return;
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
}
