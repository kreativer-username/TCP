package server;


import data.Stream;

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

    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer();
        String input = tcpServer.read();
        System.out.println("read (S): " + input);
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
        System.out.println("waiting for client...");
        client = server.accept();
        System.out.println("Client is connected!");
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

    public String read() throws IOException {
        String input;
        byte[] buffer = new byte[100];
        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couln't read from InputStream!");
            return null;
        }

        input = new String(buffer);
        if (input.charAt(0) == '@') {
            String[] arguments = input.split(" ");
            System.out.println("found an argument...");
            System.out.println("Arguments[0]=" + arguments[0] + " Argumentlength: " + arguments.length);
            switch (arguments[0]) {
                case "@FILE":
                    if (arguments.length == 3) {
                        saveFile(arguments[1], arguments[2]);
                        break;
                    } else {
                        throw new IOException("Wrong arguments!");
                    }
            }
        }
        return input;
    }

    private boolean saveFile(String filename, String data) {
        System.out.println("Trying to save the file...");
        boolean savingState = false;
        try {
            savingState = Stream.write(data, "../out/" + filename);
            write("@SAVED");
        } catch (IOException e) {
            System.err.println("Couldn't save file!");
        }
        return savingState;
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
