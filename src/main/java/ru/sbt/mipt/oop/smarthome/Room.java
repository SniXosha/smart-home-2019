package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements Actionable {
    private final Collection<Actionable> devices;
    private final String name;

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

    public void debug() {
        for (Actionable device : devices) {
            System.out.println(device);
        }
    }
}
