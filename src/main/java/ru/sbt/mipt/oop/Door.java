package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door implements Device {
    private final String id;
    private boolean isOpen;
    private final String type = "door";

    public Door(String id, boolean isOpen) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String getType() {
        return "door";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() == DOOR_OPEN) {
            setOpen(true);
        } else if (event.getType() == DOOR_CLOSED) {
            setOpen(false);
        }
    }
}
