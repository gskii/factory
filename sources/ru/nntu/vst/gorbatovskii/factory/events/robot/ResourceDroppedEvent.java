package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;

public class ResourceDroppedEvent extends RobotEvent<ResourceDroppedHandler> {

    public ResourceDroppedEvent(double time, ResourceDroppedHandler handler, ResourceProducer producer, ResourceConsumer consumer) {
        super(time, handler, producer, consumer);
    }

    @Override
    protected void handle() {
        handler.onResourceDropped(this);
    }

    @Override
    public String toString() {
        return handler.toString() + ".ResourceDroppedEvent{" + getConsumer() + "}";
    }
}