package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.StaticAction;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

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
