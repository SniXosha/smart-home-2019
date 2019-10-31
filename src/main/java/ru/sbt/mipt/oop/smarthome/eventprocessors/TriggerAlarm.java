package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.alarm.*;
import ru.sbt.mipt.oop.smarthome.signals.SignalSender;

public class TriggerAlarm implements EventProcessor {

    private final HomeAlarm homeAlarm;
    private final EventProcessor delegate;
    private final SignalSender signalSender;

    public TriggerAlarm(HomeAlarm homeAlarm, EventProcessor delegate, SignalSender signalSender) {
        this.delegate = delegate;
        this.signalSender = signalSender;
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void processEvent(Object event) {
        HomeAlarmState state = homeAlarm.getState();
        if (state instanceof DeactivatedAlarm) {
            delegate.processEvent(event);
        }
        if (state instanceof ActivatedAlarm) {
            homeAlarm.activateDangerAlarm();
        }
        if (state instanceof ActivatedAlarm || state instanceof ActivatedDangerAlarm) {
            signalSender.send("Alarm!!! Denied event (" + event.toString() + ")");
        }
    }
}
