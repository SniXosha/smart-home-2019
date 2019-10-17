package ru.sbt.mipt.oop.smarthome;

public class DoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    @Override
    public void processEvent(SensorEvent event) {
        Device device = smartHome.getDevice(event.getObjectId());
        if (device instanceof Door) {
            if (event.getType() == SensorEventType.DOOR_CLOSED) {
                ((Door) device).setOpen(false);
            } else if (event.getType() == SensorEventType.DOOR_OPEN){
                ((Door) device).setOpen(true);
            }
        }
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}
