package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

import java.util.Collection;
import java.util.Iterator;

public class SmartHome implements Actionable, Iterable<Actionable> {
    private final Collection<Actionable> rooms;

    public SmartHome(Collection<Actionable> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Iterator<Actionable> iterator() {
        return rooms.iterator();
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
