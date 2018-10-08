package ru.nntu.vst.gorbatovskii.factory.model.common;

public interface ResourceProducer extends HasLocation {

    Resource produceResource();

    boolean hasResources();
}
