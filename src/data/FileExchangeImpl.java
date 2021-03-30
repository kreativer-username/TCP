package data;

import java.io.*;

public class FileExchangeImpl implements FileExchange {
    @Override
    public void sendFile(String path, OutputStream os) throws FileNotFoundException {
        String input;
        byte[] buffer = new byte[100];
        InputStream is;
        is = new FileInputStream(path);

        try {
            is.read(buffer);
        } catch (IOException e) {
            System.err.println("Couldn't read from file!");
            return;
        }
        input = new String(buffer);

        PrintStream ps = new PrintStream(os);
        ps.println(input);
    }

    @Override
    public void saveFile(String path, InputStream is) throws IOException {
        OutputStream os;
        try {
            os = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            os = new FileOutputStream(path);
        }

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String fileData;
        try {
            fileData = br.readLine();
        } catch (IOException e) {
            System.err.println("Couldn't read out of InputStream!");
            return;
        }

        try {
            os.write(fileData.getBytes());
        } catch (IOException e) {
            System.err.println("Couldn't write in file!");
            return;
        }
        System.out.println("Successfully wrote in file " + path);
    }
}
