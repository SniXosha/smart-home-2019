package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.ActivateDangerAlarmAction;

public class ActivateDangerAlarmCommand extends SmartHomeActionCommand {
    public ActivateDangerAlarmCommand(SmartHome smartHome) {
        super(smartHome, new ActivateDangerAlarmAction());
    }
}
