package ru.nntu.vst.gorbatovskii.factory.model.robot;

import ru.nntu.vst.gorbatovskii.factory.model.common.HasLocation;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;

public interface Robot extends HasLocation {

    void transferResource(ResourceProducer producer, ResourceConsumer consumer);

    boolean isOccupied();
}
