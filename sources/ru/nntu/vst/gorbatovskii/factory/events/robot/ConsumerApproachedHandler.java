package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.Handler;

public interface ConsumerApproachedHandler extends Handler {

    void onConsumerApproached(ConsumerApproachedEvent event);
}
