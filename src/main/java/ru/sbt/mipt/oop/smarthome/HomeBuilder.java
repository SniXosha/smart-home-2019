package ru.sbt.mipt.oop.smarthome;

import java.io.IOException;

public interface HomeBuilder {

    SmartHome loadSmartHome() throws IOException;

    void dumpSmartHome() throws IOException;
}
