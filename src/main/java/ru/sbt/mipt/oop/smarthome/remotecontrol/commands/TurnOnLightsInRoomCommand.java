package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.TurnOnLightsInRoomAction;

public class TurnOnLightsInRoomCommand extends SmartHomeActionCommand {
    public TurnOnLightsInRoomCommand(SmartHome smartHome, String roomName) {
        super(smartHome, new TurnOnLightsInRoomAction(roomName));
    }
}
