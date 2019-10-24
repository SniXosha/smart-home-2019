package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.smarthome.eventprocessors.LightEventProcessor;

import java.util.Arrays;
import java.util.List;

class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        HomeBuilder homeBuilder = new SimpleHome();
        SmartHome smartHome = homeBuilder.loadSmartHome();
        if (smartHome == null) return;

        List<EventProcessor> processors = Arrays.asList(new DoorEventProcessor(smartHome),
                new LightEventProcessor(smartHome), new HallDoorEventProcessor(smartHome));
        HomeProcessor homeProcessor = new HomeProcessor(processors);
        HomeRunner homeRunner = new HomeRunner(homeProcessor);

        // начинаем цикл обработки событий
        homeRunner.run();
    }
}
