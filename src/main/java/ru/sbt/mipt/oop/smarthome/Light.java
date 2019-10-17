package ru.sbt.mipt.oop.smarthome;

public class Light implements Actionable, Device {
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
    public void execute(Action action) {
        if (action.getId() != null && !action.getId().equals(id)) {
            return;
        }
        if (!action.getTargetClass().equals(this.getClass())) {
            return;
        }
        if (action.getCommand().equals("on")) {
            setOn(true);
        } else if (action.getCommand().equals("off")) {
            setOn(false);
        }
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
