package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;

import java.util.Collection;

public class SmartHome implements Actionable {
    private final Collection<Actionable> rooms;
    private final HomeAlarm homeAlarm;

    public SmartHome(Collection<Actionable> rooms, HomeAlarm homeAlarm) {
        this.rooms = rooms;
        this.homeAlarm = homeAlarm;
    }

    public HomeAlarm getHomeAlarm() {
        return homeAlarm;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        action.execute(homeAlarm);
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
