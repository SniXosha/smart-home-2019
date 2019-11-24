package ru.sbt.mipt.oop;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.remotecontrol.RemoteButtonType;
import ru.sbt.mipt.oop.smarthome.remotecontrol.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.*;

@Configuration
public class RemoteControlConfiguration {
    private final String RC_ID = "1";
    private final String ALARM_CODE = "code";

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    RemoteControl remoteControl(RemoteControlCommand[] commands) {

        RemoteButtonType[] buttons = RemoteButtonType.values();
        SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl();
        for (int i = 0; i < commands.length; i++) {
            smartHomeRemoteControl.setCommand(RemoteButtonType.getCodeFromButton(buttons[i]), commands[i]);
        }
        remoteControlRegistry().registerRemoteControl(smartHomeRemoteControl, RC_ID);
        return smartHomeRemoteControl;
    }

    @Bean
    RemoteControlCommand activateAlarmCommand(SmartHome smartHome) {
        return new ActivateAlarmCommand(smartHome, ALARM_CODE);
    }

    @Bean
    RemoteControlCommand activateDangerAlarmCommand(SmartHome smartHome) {
        return new ActivateDangerAlarmCommand(smartHome);
    }

    @Bean
    RemoteControlCommand closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    RemoteControlCommand turnOnAllLightsCommand(SmartHome smartHome) {
        return new SetAllLightsCommand(smartHome, true);
    }

    @Bean
    RemoteControlCommand turnOffAllLightsCommand(SmartHome smartHome) {
        return new SetAllLightsCommand(smartHome, false);
    }

    @Bean
    RemoteControlCommand turnOnLightsInHallCommand(SmartHome smartHome) {
        return new TurnOnLightsInRoomCommand(smartHome, "hall");
    }
}
