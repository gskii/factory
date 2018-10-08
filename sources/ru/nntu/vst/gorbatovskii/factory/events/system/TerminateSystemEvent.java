package ru.nntu.vst.gorbatovskii.factory.events.system;

import ru.nntu.vst.gorbatovskii.factory.events.GlobalEvent;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TerminateSystemEvent extends GlobalEvent<TerminateSystemHandler> {

    public TerminateSystemEvent(double duration) {
        super(duration, TerminateSystemHandler.class);
    }

    @Override
    protected void handle() {
        List<TerminateSystemHandler> handlers = new LinkedList<>(getHandlers());
        handlers.sort(new Comparator<TerminateSystemHandler>() {
            @Override
            public int compare(TerminateSystemHandler h1, TerminateSystemHandler h2) {
                return Integer.compare(h1.getOrder(), h2.getOrder());
            }
        });

        for (TerminateSystemHandler handler : handlers) {
            handler.onSystemTerminate(this);
        }
    }

    @Override
    public String toString() {
        return "system.TerminateSystemEvent";
    }
}
