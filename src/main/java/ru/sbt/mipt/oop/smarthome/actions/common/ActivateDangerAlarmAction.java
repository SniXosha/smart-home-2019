package ru.sbt.mipt.oop.smarthome.actions.common;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;

public class ActivateDangerAlarmAction implements Action {
    @Override
    public void execute(Object obj) {
        if (obj instanceof SmartHome) {
            HomeAlarm homeAlarm = ((SmartHome) obj).getHomeAlarm();
            homeAlarm.activateDangerAlarm();
        }
    }
}
