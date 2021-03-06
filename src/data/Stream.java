package data;

import java.io.*;

/**
 * Bietet Methoden für I/O an
 */
public class Stream {

    /**
     * Schreibt die angegebene Nachricht in die bereits existierende Datei unter dem Dateipfad
     * @param message Nachricht, die in die Datei geschrieben werden soll
     * @param filename Path der Datei
     * @return true, wenn das Hineinschreiben erfolgreich war, false, wenn die Datei nicht geöffnet oder nicht hineingeschrieben werden konnte
     */
    public static boolean write(String message, String filename) throws IOException {
        OutputStream os;
        try {
            os = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            File file = new File(filename);
            file.getParentFile().mkdirs();
            file.createNewFile();
            os = new FileOutputStream(filename);
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
