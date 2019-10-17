package ru.sbt.mipt.oop.smarthome;

public class LightEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    @Override
    public void processEvent(SensorEvent event) {
        Device device = smartHome.getDevice(event.getObjectId());
        if (device instanceof Light) {
            if (event.getType() == SensorEventType.LIGHT_OFF) {
                ((Light) device).setOn(false);
            } else if (event.getType() == SensorEventType.LIGHT_ON){
                ((Light) device).setOn(true);
            }
        }
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}
