package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;

public class ProducerApproachedEvent extends RobotEvent<ProducerApproachedHandler> {

    public ProducerApproachedEvent(double time, ProducerApproachedHandler handler, ResourceProducer producer, ResourceConsumer consumer) {
        super(time, handler, producer, consumer);
    }

    @Override
    protected void handle() {
        handler.onProducerApproached(this);
    }

    @Override
    public String toString() {
        return handler.toString() + ".ProducerApproachedEvent{" + getConsumer() + " -> " + getProducer() + "}";
    }
}
