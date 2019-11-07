package ru.sbt.mipt.oop.smarthome;

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
}
