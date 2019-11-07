package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;

public class Room {
    private Collection<Device> devices;
    private String name;

    public Room(String name, Collection<Device> devices) {
        this.devices = devices;
        this.name = name;
    }

    public Room(String name) {
        this(name, new ArrayList<>());
    }

    public Collection<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public String getName() {
        return name;
    }
}
