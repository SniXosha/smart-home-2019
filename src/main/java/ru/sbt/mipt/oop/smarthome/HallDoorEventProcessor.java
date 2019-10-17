package ru.sbt.mipt.oop.smarthome;

public class HallDoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;
    public static String roomInitAnswer = "/room";
    public static String deviceInitAnswer = "/device";

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) {
            return;
        }

        Action hallDoorAction = new StaticAction(Room.class, event.getObjectId(), "halldoor");
        hallDoorAction.setAnswer(HallDoorEventProcessor.roomInitAnswer);
        smartHome.execute(hallDoorAction);
        if (!hallDoorAction.getAnswer().equals("true")) {
            return;
        }
        smartHome.execute(new StaticAction(Door.class, event.getObjectId(), "close"));
        smartHome.execute(new StaticAction(Light.class, null, "off"));
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}
