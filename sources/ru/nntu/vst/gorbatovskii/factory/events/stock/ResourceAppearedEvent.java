package ru.nntu.vst.gorbatovskii.factory.events.stock;

import ru.nntu.vst.gorbatovskii.factory.events.LocalEvent;

public class ResourceAppearedEvent extends LocalEvent<ResourceAppearedHandler> {

    public ResourceAppearedEvent(double duration, ResourceAppearedHandler handler) {
        super(duration, handler);
    }

    @Override
    protected void handle() {
        handler.onResourceAppeared();
    }

    @Override
    public String toString() {
        return "stock.ResourceAppearedEvent";
    }
}
