package ru.sbt.mipt.oop.smarthome.actions.common;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Door;

public class CloseHallDoorAction implements Action {
    @Override
    public void execute(Object obj) {
        if (obj instanceof Door && ((Door) obj).getRoomName().equals("hall")) {
            ((Door) obj).setOpen(false);
        }
    }
}
