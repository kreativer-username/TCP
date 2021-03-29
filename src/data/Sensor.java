package data;

public class Sensor {
    private long timestamp;
    private float value;
    private String name;

    public Sensor(String n, float v) {
        name = n;
        value = v;
        timestamp = System.currentTimeMillis();
    }

    public Sensor(String n, long t, float v) {
        name = n;
        value = v;
        timestamp = t;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
