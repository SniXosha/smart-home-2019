package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class SmartHome {
    private final Collection<Room> rooms;

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome() {
        this(new ArrayList<>());
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Device getDevice(String id) {
        for (Room room : rooms) {
            Device device = room.getDevice(id);
            if (device != null) {
                return device;
            }
        }
        return null;
    }

    public Room getRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public Collection<Device> getDevicesOfClass(Class<?> clazz) {
        ArrayList<Device> result = new ArrayList<>();
        for (Room room : rooms) {
            Map<String, Device> devices = room.getDevices();
            for (Device device : devices.values()) {
                if (clazz.isAssignableFrom(device.getClass())) {
                    result.add(device);
                }
            }
        }
        return result;
    }
}
