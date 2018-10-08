package ru.nntu.vst.gorbatovskii.factory.events.machine;

import ru.nntu.vst.gorbatovskii.factory.events.LocalEvent;
import ru.nntu.vst.gorbatovskii.factory.model.common.Machine;

public class ResourceProcessedEvent extends LocalEvent<ResourceProcessedHandler> {

    private Machine machine;

    public ResourceProcessedEvent(double duration, ResourceProcessedHandler handler, Machine machine) {
        super(duration, handler);
        this.machine = machine;
    }

    @Override
    protected void handle() {
        handler.onResourceProcessed();
    }

    @Override
    public String toString() {
        return "machine.ResourceProcessedEvent{" + machine + '}';
    }
}
