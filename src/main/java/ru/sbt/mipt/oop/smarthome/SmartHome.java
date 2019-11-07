package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;

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
}
