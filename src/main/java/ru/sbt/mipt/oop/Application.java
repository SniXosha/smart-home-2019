package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.eventprocessors.*;
import ru.sbt.mipt.oop.smarthome.serialization.HomeBuilder;
import ru.sbt.mipt.oop.smarthome.serialization.SimpleHome;
import ru.sbt.mipt.oop.smarthome.signals.ConsoleSignalSender;
import ru.sbt.mipt.oop.smarthome.signals.SignalSender;

import java.util.Arrays;
import java.util.List;

class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        HomeBuilder homeBuilder = new SimpleHome();
        SmartHome smartHome = homeBuilder.loadSmartHome();
        if (smartHome == null) return;
        SignalSender signalSender = new ConsoleSignalSender();

        List<EventProcessor> processors = Arrays.asList(
                new AlarmEventProccesor(smartHome),
                new DoorEventProcessor(smartHome),
                new LightEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome));
        EventProcessor homeProcessor = new TriggerAlarm(smartHome.getHomeAlarm(),
                new HomeProcessor(processors), signalSender);
        HomeRunner homeRunner = new HomeRunner(homeProcessor);

        // начинаем цикл обработки событий
        homeRunner.run();
    }
}
