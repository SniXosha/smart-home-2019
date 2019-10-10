package ru.sbt.mipt.oop;

import java.util.Map;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class TurnOffAllLights implements ComplexTrigger {

    private SmartHome smartHome;
    private final String type = "turnoffalllights";

    TurnOffAllLights() {
        smartHome = null;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        boolean isTriggered = false;
        for (Room room : smartHome.getRooms()) {
            if (!room.getName().equals("hall")) {
                continue;
            }
            Map<String, Device> devices = room.getDevices();
            if (devices.containsKey(event.getObjectId())) {
                isTriggered = true;
            }
        }
        if (isTriggered) {
            for (Room room : smartHome.getRooms()) {
                Map<String, Device> devices = room.getDevices();
                for (Device device : devices.values()) {
                    if (device.getType().equals("light")) {
                        ((Light) device).setOn(false);
                    }
                }
            }
        }
    }
}
