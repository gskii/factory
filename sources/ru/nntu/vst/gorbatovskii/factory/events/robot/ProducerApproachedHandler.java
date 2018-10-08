package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.Handler;

public interface ProducerApproachedHandler extends Handler {

    void onProducerApproached(ProducerApproachedEvent event);
}
