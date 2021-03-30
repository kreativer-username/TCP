package data;

import java.io.*;

public interface FileExchange {
    /**
     * Sendet eine im Parameter angegebene File in den OutputStream
     * @param path Pfad zu der zusendenden Datei
     * @param os OutputStream, in den die Datei geschrieben werden soll
     * @throws FileNotFoundException falls es keine Datei, wie in dem Pfad angegeben, gibt
     */
    void sendFile(String path, OutputStream os) throws FileNotFoundException;

    /**
     * Liesst eine File aus dem angegebenen InputStream und speichert diese im angegebenen Pfad
     * @param is InputStream, aus dem die Datei gelesen werden soll
     * @param path Pfad, wo die Datei hingespeichert werden soll
     * @return gelesene Datei
     * @throws IOException falls keine Datei aus dem InputStream gelesen werden kann
     */
    void saveFile(String path, InputStream is) throws IOException;
}
