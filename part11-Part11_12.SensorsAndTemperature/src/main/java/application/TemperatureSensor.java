package application;

import java.util.Random;

public class TemperatureSensor implements Sensor {
    private boolean isSensorOn;
    private final Random random;

    public TemperatureSensor() {
        this.isSensorOn = false; // Initially off
        this.random = new Random();
    }

    @Override
    public boolean isOn() {
        return this.isSensorOn;
    }

    @Override
    public void setOn() {
        this.isSensorOn = true;
    }

    @Override
    public void setOff() {
        this.isSensorOn = false;
    }

    @Override
    public int read() {
        // Rule: If the sensor is off, throw an IllegalStateException
        if (!this.isSensorOn) {
            throw new IllegalStateException("The sensor is currently turned off.");
        }

        // nextInt(61) yields 0 to 60. Subtracting 30 shifts the range to -30 to 30.
        return this.random.nextInt(61) - 30;
    }
}
