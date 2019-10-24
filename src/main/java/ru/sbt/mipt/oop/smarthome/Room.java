package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Room implements Actionable, Iterable<Actionable> {
    private final Collection<Actionable> devices;
    private final String name;
    @SuppressWarnings("FieldCanBeLocal")
    private final String type = "room";

    public Room(String name) {
        this.devices = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addActionable(Actionable actionable) {
        devices.add(actionable);
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        for (Actionable device : devices) {
            device.execute(action);
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Iterator<Actionable> iterator() {
        return devices.iterator();
    }
}
