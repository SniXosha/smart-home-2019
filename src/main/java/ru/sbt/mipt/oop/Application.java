package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.smarthome.eventprocessors.LightEventProcessor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = HomeBuilder.loadSmartHome();

        List<EventProcessor> processors = Arrays.asList(new DoorEventProcessor(),
                new LightEventProcessor(), new HallDoorEventProcessor());
        HomeProcessor homeProcessor = new HomeProcessor(smartHome, processors);
        HomeRunner homeRunner = new HomeRunner(homeProcessor);

        // начинаем цикл обработки событий
        homeRunner.run();
    }
}
