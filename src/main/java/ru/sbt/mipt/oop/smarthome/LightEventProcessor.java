package ru.sbt.mipt.oop.smarthome;

public class LightEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        Device device = getDevice(event);
        if (device == null) return;

        if (device instanceof Light) {
            if (event.getType() == SensorEventType.LIGHT_OFF) {
                ((Light) device).setOn(false);
            } else if (event.getType() == SensorEventType.LIGHT_ON) {
                ((Light) device).setOn(true);
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
