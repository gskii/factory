package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;

public class ResourceGrabbedEvent extends RobotEvent<ResourceGrabbedHandler> {

    public ResourceGrabbedEvent(double time, ResourceGrabbedHandler handler, ResourceProducer producer, ResourceConsumer consumer) {
        super(time, handler, producer, consumer);
    }

    @Override
    protected void handle() {
        handler.onResourceGrabbed(this);
    }

    @Override
    public String toString() {
        return handler.toString() + ".ResourceGrabbedEvent{" + getProducer() + "}";
    }
}
