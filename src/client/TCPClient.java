package client;

import data.Sensor;
import data.Stream;

import java.io.*;
import java.net.Socket;

public class TCPClient {

    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public static void main(String[] args) {
        TCPClient client = new TCPClient();

        if (client.connect("localhost", 1234)) {
            Sensor sensorData = new Sensor("test", 123);
            client.sendSensorData(sensorData);
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

    public void sendFile(String filepath) {
        String[] temp = filepath.split("/");
        String fileName = temp[temp.length - 1];
        String file = Stream.read(filepath);

        send("@FILE " + fileName + " " + file);
        System.out.println("filename: " + fileName);
        System.out.println("data: "+ file);
    }

    public void sendSensorData(Sensor data) {
        send("@DATA " + data.getName() + " " + data.getTimestamp() + " " + data.getValue());
    }

    public static String read(String filename) {
        String input;
        byte[] buffer = new byte[100];
        InputStream is;
        try {
            is = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file!");
            return null;
        }

        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couldn't read from file!");
            return null;
        }
        input = new String(buffer);

        return input;
    }

    public String read() {
        String input;
        byte[] buffer = new byte[100];
        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couldn't read from InputStream!");
            return null;
        }

        input = new String(buffer);

        if (input.charAt(0) == '@') {
            String[] inputSplitted = input.split(" ");
            if (inputSplitted[0].equals("@DATA") && inputSplitted.length == 4) {
                Sensor data = readSensorData(inputSplitted[1], Long.parseLong(inputSplitted[2]), Float.parseFloat(inputSplitted[3]));
                input = "Read sensor data: " + data;
            }
        }
        return input;
    }

    private Sensor readSensorData(String name, long timestamp, float value) {
        return new Sensor(name, timestamp, value);
    }

    public String read(int length) {
        String input;
        byte[] buffer = new byte[length];
        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couldn't read from InputStream!");
            return null;
        }

        input = new String(buffer);
        return input;
    }
}
