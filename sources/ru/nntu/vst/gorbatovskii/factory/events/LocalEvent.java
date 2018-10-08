package ru.nntu.vst.gorbatovskii.factory.events;

public abstract class LocalEvent<H> extends Event {

    protected H handler;

    public LocalEvent(double duration, H handler) {
        super(duration);
        this.handler = handler;
    }
}
