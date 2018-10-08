package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;

public class ConsumerApproachedEvent extends RobotEvent<ConsumerApproachedHandler> {

    public ConsumerApproachedEvent(double time, ConsumerApproachedHandler handler, ResourceProducer producer, ResourceConsumer consumer) {
        super(time, handler, producer, consumer);
    }

    @Override
    protected void handle() {
        handler.onConsumerApproached(this);
    }
}
