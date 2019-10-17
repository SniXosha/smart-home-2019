package ru.sbt.mipt.oop.smarthome;

import java.util.Collection;

public class HallDoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) {
            return;
        }

        Room room = smartHome.getRoom("hall");
        if (room == null) {
            return;
        }
        Device device = room.getDevice(event.getObjectId());
        if (device == null) {
            return;
        }
        if (!(device instanceof Door)) {
            return;
        }
        ((Door) device).setOpen(false);

        Collection<Device> lightDevices = smartHome.getDevicesOfClass(Light.class);
        for (Device lightDevice : lightDevices) {
            ((Light) lightDevice).setOn(false);
        }
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}
