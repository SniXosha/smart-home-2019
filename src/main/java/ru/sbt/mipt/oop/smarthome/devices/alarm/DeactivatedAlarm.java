package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class DeactivatedAlarm extends HomeAlarmState {

    @SuppressWarnings("FieldCanBeLocal")
    private final String type = "deactivated";

    DeactivatedAlarm(HomeAlarm homeAlarm) {
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void activate(String code) {
        homeAlarm.setState(new ActivatedAlarm(homeAlarm, code));
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void activateDangerAlarm() {
    }

    @Override
    public String getStateType() {
        return type;
    }

}
