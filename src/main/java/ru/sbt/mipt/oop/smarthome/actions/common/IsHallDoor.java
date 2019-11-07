package ru.sbt.mipt.oop.smarthome.actions.common;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Door;

public class IsHallDoor implements Action {

    private final String doorId;
    private boolean result = false;

    public IsHallDoor(String doorId) {
        this.doorId = doorId;
    }

    @Override
    public void execute(Object obj) {
        if (obj instanceof Door) {
            Door door = (Door) obj;
            if (door.getId().equals(doorId) && door.getRoomName().equals("hall")) {
                result = true;
            }
        }
    }

    public boolean check() {
        return result;
    }
}
