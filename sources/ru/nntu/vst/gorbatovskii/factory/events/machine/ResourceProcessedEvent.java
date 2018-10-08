package ru.nntu.vst.gorbatovskii.factory.events.machine;

import ru.nntu.vst.gorbatovskii.factory.events.LocalEvent;

/**
 * Created by GSCII on 07.10.2018.
 */
public class ResourceProcessedEvent extends LocalEvent<ResourceProcessedHandler> {

    public ResourceProcessedEvent(double duration, ResourceProcessedHandler handler) {
        super(duration, handler);
    }

    @Override
    protected void handle() {

    }
}
