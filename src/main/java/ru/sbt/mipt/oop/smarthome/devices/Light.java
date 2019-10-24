package ru.sbt.mipt.oop.smarthome.devices;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

public class Light implements Actionable, Device {
    private final String id;
    private boolean isOn;
    @SuppressWarnings("FieldCanBeLocal")
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
    public void execute(Action action) {
        action.execute(this);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getId() {
        return id;
    }
}
