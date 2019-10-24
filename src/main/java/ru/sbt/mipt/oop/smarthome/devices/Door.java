package ru.sbt.mipt.oop.smarthome.devices;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

public class Door implements Device, Actionable {
    private final String id;
    private boolean isOpen;
    @SuppressWarnings("FieldCanBeLocal")
    private final String type = "door";
    private final String roomName;

    public Door(String id, boolean isOpen, String roomName) {
        this.isOpen = isOpen;
        this.id = id;
        this.roomName = roomName;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getRoomName() {
        return roomName;
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

    public boolean isOpen() {
        return isOpen;
    }
}
