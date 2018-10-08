package ru.nntu.vst.gorbatovskii.factory.events;

public interface EventBus {

    void registerEvent(Event event);

    void executeNext();

}
