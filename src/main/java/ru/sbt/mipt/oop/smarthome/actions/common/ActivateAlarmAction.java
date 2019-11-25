package ru.sbt.mipt.oop.smarthome.actions.common;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;

public class ActivateAlarmAction implements Action {
    private final String code;

    public ActivateAlarmAction(String code) {
        this.code = code;
    }

    @Override
    public void execute(Object obj) {
        if (obj instanceof SmartHome) {
            HomeAlarm homeAlarm = ((SmartHome) obj).getHomeAlarm();
            homeAlarm.activate(code);
        }
    }
}
