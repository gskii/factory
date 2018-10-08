package ru.nntu.vst.gorbatovskii.factory.model.common;

public interface ResourceConsumer extends HasLocation {

    void onResourceAppeared(ResourceProducer producer);

    void consumeResource(Resource resource);
}
