package ru.nntu.vst.gorbatovskii.factory.model.common;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.events.EventBus;
import ru.nntu.vst.gorbatovskii.factory.events.stock.ResourceAppearedEvent;
import ru.nntu.vst.gorbatovskii.factory.events.stock.ResourceAppearedHandler;
import ru.nntu.vst.gorbatovskii.factory.model.time.TimeCalculationStrategy;

import java.util.LinkedList;

public class Stock implements ResourceProducer, ResourceAppearedHandler {

    private EventBus eventBus;
    private TimeCalculationStrategy timeCalculationStrategy;

    private Location location;
    private ResourceConsumer consumer;
    private LinkedList<Resource> resources;

    public Stock() {
        resources = new LinkedList<>();
    }

    @Override
    public void onResourceAppeared() {
        resources.add(new Resource());
        consumer.onResourceAppeared();
        eventBus.registerEvent(
                new ResourceAppearedEvent(timeCalculationStrategy.getProcessingTime(), this)
        );
    }

    @Override
    public Resource produceResource() {
        return resources.pop();
    }

    @Override
    public boolean hasResources() {
        return !resources.isEmpty();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Required
    public void setConsumer(ResourceConsumer consumer) {
        this.consumer = consumer;
    }

    @Required
    public void setLocation(Location location) {
        this.location = location;
    }

    @Required
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Required
    public void setTimeCalculationStrategy(TimeCalculationStrategy timeCalculationStrategy) {
        this.timeCalculationStrategy = timeCalculationStrategy;
    }
}
