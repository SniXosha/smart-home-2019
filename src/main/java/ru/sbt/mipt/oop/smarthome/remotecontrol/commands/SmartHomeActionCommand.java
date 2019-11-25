package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;

public class SmartHomeActionCommand implements RemoteControlCommand {
    private final SmartHome smartHome;
    private final Action action;

    public SmartHomeActionCommand(SmartHome smartHome, Action action) {
        this.smartHome = smartHome;
        this.action = action;
    }

    @Override
    public void execute() {
        smartHome.execute(action);
    }
}
