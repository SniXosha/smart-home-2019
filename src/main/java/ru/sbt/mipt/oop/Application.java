package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeBuilder homeBuilder = new SimpleHome();
        SmartHome smartHome = homeBuilder.loadSmartHome();

        List<EventProcessor> processors = Arrays.asList(new DoorEventProcessor(smartHome),
                new LightEventProcessor(smartHome), new HallDoorEventProcessor(smartHome));
        HomeProcessor homeProcessor = new HomeProcessor(processors);
        HomeRunner homeRunner = new HomeRunner(homeProcessor);

        // начинаем цикл обработки событий
        homeRunner.run();
    }
}
