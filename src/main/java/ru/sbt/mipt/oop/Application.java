package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = HomeBuilder.loadSmartHome();

        // начинаем цикл обработки событий
        HomeRunner.run(smartHome);
    }
}
