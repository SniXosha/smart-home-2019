package ru.sbt.mipt.oop.smarthome;

public class DoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        Device eventDevice = getDevice(event);
        if (eventDevice == null) return;

        if (eventDevice instanceof Door) {
            if (event.getType() == SensorEventType.DOOR_CLOSED) {
                ((Door) eventDevice).setOpen(false);
            } else if (event.getType() == SensorEventType.DOOR_OPEN) {
                ((Door) eventDevice).setOpen(true);
            }
        }
    }

    private Device getDevice(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Device device : room.getDevices()) {
                if (device.getId().equals(event.getObjectId())) {
                    return device;
                }
            }
        }
        return null;
    }
}
