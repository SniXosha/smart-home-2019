package ru.sbt.mipt.oop.smarthome.signals;

public class ConsoleSignalSender implements SignalSender {
    @Override
    public void send(String message) {
        System.out.println(message);
    }
}
