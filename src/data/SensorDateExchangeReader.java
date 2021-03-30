package data;

import java.io.IOException;
import java.io.InputStream;

public interface SensorDateExchangeReader {

    /**
     * Liesst eine Sensordatum aus dem angegebene InputStream
     * @param is InputStream, aus dem ein Sensordatum gelesen werden soll
     * @return Sensordatum
     * @throws IOException wenn kein Sensordatum aus dem angegebenen InputSteam gelesen werden kann
     */
    Sensor readSensorData(InputStream is) throws IOException;
}
