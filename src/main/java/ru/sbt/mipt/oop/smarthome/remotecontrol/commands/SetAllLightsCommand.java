package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.SetAllLightsAction;

public class SetAllLightsCommand extends SmartHomeActionCommand {
    public SetAllLightsCommand(SmartHome smartHome, boolean state) {
        super(smartHome, new SetAllLightsAction(state));
    }
}