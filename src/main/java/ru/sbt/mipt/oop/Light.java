package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light implements Device {
    private final String id;
    private boolean isOn;
    private final String type = "light";

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() == LIGHT_ON) {
            setOn(true);
        } else if (event.getType() == LIGHT_OFF) {
            setOn(false);
        }
    }
}
