package ru.sbt.mipt.oop.smarthome.alarm;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

public class HomeAlarm implements Alarm, Actionable {

    private HomeAlarmState state;

    public HomeAlarm() {
        this.state = new DeactivatedAlarm(this);
    }

    public HomeAlarmState getState() {
        return state;
    }

    void setState(HomeAlarmState newState) {
        this.state = newState;
    }

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void activateDangerAlarm() {
        state.activateDangerAlarm();
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
