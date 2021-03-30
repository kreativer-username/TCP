package data;

import java.io.IOException;
import java.io.OutputStream;

public interface SensorDateExchangeWriter {

    /**
     * Schreibt das angegebene Sensordatum in den OutputStream
     * @param sensor Sensordatum, das verschickt werden soll
     * @param os OutputStream, in den das Sensordatum geschrieben werden soll
     * @throws IOException wenn das Sensordatum nicht in den OutputStream geschieben werden kann
     */
    void writeSensorDate(Sensor sensor, OutputStream os) throws IOException;
}
