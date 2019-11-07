package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.CloseHallDoorAction;

public class CloseHallDoorCommand extends SmartHomeActionCommand {
    public CloseHallDoorCommand(SmartHome smartHome) {
        super(smartHome, new CloseHallDoorAction());
    }
}
