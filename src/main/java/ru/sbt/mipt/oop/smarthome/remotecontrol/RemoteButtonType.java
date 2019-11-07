package ru.sbt.mipt.oop.smarthome.remotecontrol;

public enum RemoteButtonType {
    ButtonA,
    ButtonB,
    ButtonC,
    ButtonD,
    Button1,
    Button2,
    Button3,
    Button4;

    public static RemoteButtonType getFromCode(String code) {
        return RemoteButtonType.valueOf("Button" + code);
    }
}
