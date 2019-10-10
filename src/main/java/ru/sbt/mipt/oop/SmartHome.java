package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class SmartHome {
    private final Collection<Room> rooms;
    private final Collection<ComplexTrigger> complexTriggers;

    public SmartHome() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public SmartHome(Collection<Room> rooms, Collection<ComplexTrigger> complexTriggers) {
        this.rooms = rooms;
        this.complexTriggers = complexTriggers;
        setupComplexTriggers();
    }

    public SmartHome(Collection<Room> rooms) {
        this(rooms, new ArrayList<>());
    }

    public void setupComplexTriggers() {
        for (ComplexTrigger complexTrigger : this.complexTriggers) {
            complexTrigger.setSmartHome(this);
        }
    }

    public void addComplexTrigger(ComplexTrigger complexTrigger) {
        complexTriggers.add(complexTrigger);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void processEvent(SensorEvent event) {
        for (Room room : rooms) {
            Map<String, Device> devices = room.getDevices();
            Device device = devices.get(event.getObjectId());
            if (device != null) {
                device.processEvent(event);
            }
        }
        for (ComplexTrigger complexTrigger : complexTriggers) {
            complexTrigger.processEvent(event);
        }
    }
}
