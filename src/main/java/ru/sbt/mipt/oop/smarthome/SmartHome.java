package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

import java.util.Collection;

public class SmartHome implements Actionable {
    private final Collection<Actionable> rooms;

    public SmartHome(Collection<Actionable> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
