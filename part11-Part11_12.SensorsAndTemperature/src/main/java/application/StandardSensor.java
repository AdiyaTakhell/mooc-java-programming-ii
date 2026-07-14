/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author vanish
 */
public class StandardSensor implements Sensor {

    private int sensor;

    public StandardSensor(int sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean isOn() {
        return true;
    }

    @Override
    public int read() {
        return this.sensor;
    }

    @Override
    public void setOn() {

    }

    @Override
    public void setOff() {

    }

}
