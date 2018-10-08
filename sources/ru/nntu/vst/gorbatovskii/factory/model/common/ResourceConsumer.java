package ru.nntu.vst.gorbatovskii.factory.model.common;

import ru.nntu.vst.gorbatovskii.factory.events.stock.ResourceAppearedHandler;

public interface ResourceConsumer extends HasLocation, ResourceAppearedHandler {

    void consumeResource(Resource resource);
}
