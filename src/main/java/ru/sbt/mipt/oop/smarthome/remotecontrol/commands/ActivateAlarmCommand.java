package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.ActivateAlarmAction;

public class ActivateAlarmCommand extends SmartHomeActionCommand {
    public ActivateAlarmCommand(SmartHome smartHome, String code) {
        super(smartHome, new ActivateAlarmAction(code));
    }
}
