package ru.sbt.mipt.oop.smarthome.actions.common;

import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Light;

public class TurnOnLightsInRoomAction implements Action {
    private final String roomName;
    private final Action action;


    public TurnOnLightsInRoomAction(String roomName) {
        this.roomName = roomName;
        this.action = obj -> {
            if (obj instanceof Light) {
                ((Light) obj).setOn(true);
            }
        };
    }

    @Override
    public void execute(Object obj) {
        if (obj instanceof Room && ((Room) obj).getName().equals(roomName)) {
            ((Room) obj).execute(action);
        }
    }
}
