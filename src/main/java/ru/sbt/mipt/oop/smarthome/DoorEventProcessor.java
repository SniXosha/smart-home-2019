package ru.sbt.mipt.oop.smarthome;

public class DoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    private Action parseEvent(SensorEvent event) {
        String command = "none";
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            command = "close";
        } else if (event.getType() == SensorEventType.DOOR_OPEN) {
            command = "open";
        }
        if (command.equals("none")) {
            return null;
        }
        return new StaticAction(Door.class, event.getObjectId(), command);
    }

    @Override
    public void processEvent(SensorEvent event) {
        Action action = parseEvent(event);
        if (action != null) {
            smartHome.execute(action);
        }
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}
