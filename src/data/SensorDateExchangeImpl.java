package data;

import java.io.*;

public class SensorDateExchangeImpl implements SensorDateExchangeReader, SensorDateExchangeWriter {
    @Override
    public Sensor readSensorData(InputStream is) throws IOException {
        Sensor sensor;
        DataInputStream dis = new DataInputStream(is);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        long timestamp;
        float value;
        String name;

        timestamp = dis.readLong();
        value = dis.readFloat();
        name = br.readLine();

        sensor = new Sensor(name, timestamp, value);
        return sensor;
    }

    @Override
    public void writeSensorDate(Sensor sensor, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        PrintStream ps = new PrintStream(os);
        dos.writeLong(sensor.getTimestamp());
        dos.writeFloat(sensor.getValue());
        ps.println(sensor.getName());
    }
}
