package ru.nntu.vst.gorbatovskii.factory.events;

public abstract class Event {
    private double duration;

    public Event(double duration) {
        this.duration = duration;
    }

    double getDuration() {
        return duration;
    }

    protected abstract void handle();

}
