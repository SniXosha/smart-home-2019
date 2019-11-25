package ru.sbt.mipt.oop.smarthome.remotecontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.RemoteControlCommand;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeRemoteControlTest {

    private SmartHomeRemoteControl smartHomeRemoteControl;

    static class FirstCommand implements RemoteControlCommand {
        @Override
        public void execute() {
        }
    }


    static class SecondCommand implements RemoteControlCommand {
        @Override
        public void execute() {
        }
    }

    @BeforeEach
    void setUp() {
        smartHomeRemoteControl = new SmartHomeRemoteControl();
    }

    @Test
    void setCommandOnCorrectButton() {
        smartHomeRemoteControl.setCommand("A", new FirstCommand());
        smartHomeRemoteControl.setCommand("B", new SecondCommand());
        assertTrue(smartHomeRemoteControl.getCommand("A") instanceof FirstCommand);
        assertTrue(smartHomeRemoteControl.getCommand("B") instanceof SecondCommand);
    }

    @Test
    void setCommandOnWrongButton() {
        smartHomeRemoteControl.setCommand("!!!", new FirstCommand());
        assertNull(smartHomeRemoteControl.getCommand("!!!"));
    }
}