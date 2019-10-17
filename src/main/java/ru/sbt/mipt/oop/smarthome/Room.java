package ru.sbt.mipt.oop.smarthome;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private Map<String, Device> devices;
    private String name;

    public Room(String name, Map<String, Device> devices) {
        this.devices = devices;
        this.name = name;
    }

    public Room(String name) {
        this(name, new HashMap<String, Device>());
    }

    public Map<String, Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.put(device.getId(), device);
    }

    public String getName() {
        return name;
    }

    public Device getDevice(String id) {
        return devices.get(id);
    }
}
