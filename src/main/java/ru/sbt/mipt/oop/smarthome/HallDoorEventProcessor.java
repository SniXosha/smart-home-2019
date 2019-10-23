package ru.sbt.mipt.oop.smarthome;

public class HallDoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) {
            return;
        }

        Room hall = getHallRoom();
        if (hall == null) return;
        Door roomDoor = getDoor(event, hall);
        if (roomDoor == null) return;

        roomDoor.setOpen(false);

        for (Room room : smartHome.getRooms()) {
            for (Device device : room.getDevices()) {
                if (device instanceof Light) {
                    ((Light) device).setOn(false);
                }
            }
        }
    }

    private Door getDoor(SensorEvent event, Room room) {
        for (Device device : room.getDevices()) {
            if (device.getId().equals(event.getObjectId()) && device instanceof Door) {
                return (Door) device;
            }
        }
        return null;
    }

    private Room getHallRoom() {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                return room;
            }
        }
        return null;
    }
}
