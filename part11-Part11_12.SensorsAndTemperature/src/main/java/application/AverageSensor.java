package application;

import java.util.ArrayList;
import java.util.List;

public class AverageSensor implements Sensor {
    private final List<Sensor> sensors;
    private final List<Integer> history;

    public AverageSensor() {
        this.sensors = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public void addSensor(Sensor toAdd) {
        this.sensors.add(toAdd);
    }

    @Override
    public boolean isOn() {
        if (this.sensors.isEmpty()) {
            return false;
        }
        for (Sensor sensor : this.sensors) {
            if (!sensor.isOn()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setOn() {
        for (Sensor sensor : this.sensors) {
            sensor.setOn();
        }
    }

    @Override
    public void setOff() {
        for (Sensor sensor : this.sensors) {
            sensor.setOff();
        }
    }

    @Override
    public int read() {
        if (!this.isOn() || this.sensors.isEmpty()) {
            throw new IllegalStateException("Sensor must be on and contain sub-sensors to read.");
        }

        int sum = 0;
        for (Sensor sensor : this.sensors) {
            sum += sensor.read();
        }

        int average = sum / this.sensors.size();
        this.history.add(average); // Saves the reading to the history list
        return average;
    }

    // This method returns the results of all executed readings
    public List<Integer> readings() {
        return this.history;
    }
}
