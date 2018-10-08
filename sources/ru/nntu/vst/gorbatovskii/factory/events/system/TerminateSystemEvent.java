package ru.nntu.vst.gorbatovskii.factory.events.system;

import ru.nntu.vst.gorbatovskii.factory.events.GlobalEvent;

public class TerminateSystemEvent extends GlobalEvent<TerminateSystemHandler> {

    public TerminateSystemEvent(double duration) {
        super(duration, TerminateSystemHandler.class);
    }

    @Override
    protected void handle() {
        for (TerminateSystemHandler handler : getHandlers()) {
            handler.onSystemTerminate(this);
        }
    }
}
