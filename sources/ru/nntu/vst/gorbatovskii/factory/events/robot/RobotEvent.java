package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.LocalEvent;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;

public abstract class RobotEvent<H> extends LocalEvent<H> {

    private ResourceProducer producer;
    private ResourceConsumer consumer;

    public RobotEvent(double time, H handler,
                      ResourceProducer producer, ResourceConsumer consumer) {
        super(time, handler);
        this.producer = producer;
        this.consumer = consumer;
    }

    public ResourceProducer getProducer() {
        return producer;
    }

    public ResourceConsumer getConsumer() {
        return consumer;
    }
}
