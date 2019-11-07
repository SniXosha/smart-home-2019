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

    public void setCommand(String buttonCode, RemoteControlCommand remoteControlCommand) {
        RemoteButtonType buttonType = RemoteButtonType.getButtonFromCode(buttonCode);
        if (buttonType != null) commands.put(buttonType, remoteControlCommand);
    }

    public RemoteControlCommand getCommand(String buttonCode) {
        RemoteButtonType buttonType = RemoteButtonType.getButtonFromCode(buttonCode);
        if (buttonCode != null) {
            return commands.get(buttonType);
        } else {
            return null;
        }
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        commands.get(RemoteButtonType.getButtonFromCode(buttonCode)).execute();
    }
}
