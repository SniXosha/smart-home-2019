package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.devices.Device;

public class HomeAlarm implements Alarm, Device, Actionable {

    private final String id;
    private HomeAlarmState state;
    @SuppressWarnings("FieldCanBeLocal")
    private final String type = "homealarm";

    public HomeAlarm(String id) {
        this.id = id;
        this.state = new DeactivatedAlarm(this);
    }

    public HomeAlarmState getState() {
        return state;
    }

    void setState(HomeAlarmState newState) {
        this.state = newState;
    }

    @Override
    public String getId() {
        return id;
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

    @Override
    public String getType() {
        return type;
    }
}
