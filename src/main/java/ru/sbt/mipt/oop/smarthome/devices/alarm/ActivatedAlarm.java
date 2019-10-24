package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class ActivatedAlarm extends HomeAlarmState {

    private final String code;
    @SuppressWarnings("FieldCanBeLocal")
    private final String type = "activated";

    public ActivatedAlarm(HomeAlarm homeAlarm, String code) {
        this.homeAlarm = homeAlarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            homeAlarm.setState(new DeactivatedAlarm(homeAlarm));
        } else {
            homeAlarm.setState(new ActivatedDangerAlarm(homeAlarm, this.code));
        }
    }

    @Override
    public void activateDangerAlarm() {
        homeAlarm.setState(new ActivatedDangerAlarm(homeAlarm, this.code));
    }

    @Override
    public String getStateType() {
        return type;
    }

}