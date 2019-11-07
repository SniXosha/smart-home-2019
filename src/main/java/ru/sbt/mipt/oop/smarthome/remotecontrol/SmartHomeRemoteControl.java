package ru.sbt.mipt.oop.smarthome.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.RemoteControlCommand;

import java.util.HashMap;
import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl {

    private final Map<RemoteButtonType, RemoteControlCommand> commands;

    public SmartHomeRemoteControl() {
        commands = new HashMap<>();
    }

    public void addCommand(String buttonCode, RemoteControlCommand remoteControlCommand) {
        commands.put(RemoteButtonType.getFromCode(buttonCode), remoteControlCommand);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        commands.get(RemoteButtonType.getFromCode(buttonCode)).execute();
    }
}
