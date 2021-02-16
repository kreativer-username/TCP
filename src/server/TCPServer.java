package server;

import java.io.*;

public class TCPServer {

    public static void main(String[] args) {
        String filename = "test.txt";
        String output = "test";
        write(output, filename);
        String fileData = read(filename);
        System.out.println("read: " + fileData + ", wrote: " + output);
    }

    /**
     * Schreibt die angegebene Nachricht in die bereits existierende Datei unter dem Dateipfad
     * @param message Nachricht, die in die Datei geschrieben werden soll
     * @param filename Path der Datei
     * @return true, wenn das Hineinschreiben erfolgreich war, false, wenn die Datei nicht geöffnet oder nicht hineingeschrieben werden konnte
     */
    public static boolean write(String message, String filename) {
        OutputStream os;
        try {
            os = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file!");
            return false;
        }

        try {
            os.write(message.getBytes());
        } catch (IOException e) {
            System.err.println("Couldn't write in file!");
            return false;
        }
        System.out.println("Successfully wrote in file " + filename);
        return true;
    }

    /**
     * Liesst den Dateiinhalt der angegebenen Datei aus und gibt diesen zurück
     * @param filename Path der Datei
     * @return Dateiinhalt
     */
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
}
