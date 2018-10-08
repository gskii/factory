package ru.nntu.vst.gorbatovskii.factory.model.robot;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.events.EventBus;
import ru.nntu.vst.gorbatovskii.factory.events.robot.*;
import ru.nntu.vst.gorbatovskii.factory.model.common.Location;
import ru.nntu.vst.gorbatovskii.factory.model.common.Resource;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceProducer;
import ru.nntu.vst.gorbatovskii.factory.model.time.TimeCalculationStrategy;
import ru.nntu.vst.gorbatovskii.factory.utils.RouteUtils;

public class RobotImpl implements Robot, ProducerApproachedHandler, ConsumerApproachedHandler, ResourceGrabbedHandler, ResourceDroppedHandler {

    private EventBus eventBus;
    private TimeCalculationStrategy timeCalculationStrategy;

    private String name;

    private boolean occupied;
    private Resource resource;
    private Location location;

    /**
     * Triggers chain of actions:
     * - Approaching of producer
     * - Resource grabbing
     * - Approaching of consumer
     * - Resource dropping
     * Method execution assumes that provider has resource and consumer is empty.
     */
    @Override
    public void transferResource(ResourceProducer producer, ResourceConsumer consumer) {
        occupied = true;
        eventBus.registerEvent(
                new ProducerApproachedEvent(RouteUtils.getInstance().getRouteLength(consumer.getLocation(), producer.getLocation()),
                        this, producer, consumer)
        );
    }

    @Override
    public void onProducerApproached(ProducerApproachedEvent event) {
        ResourceProducer producer = event.getProducer();
        location = producer.getLocation();
        eventBus.registerEvent(
                new ResourceGrabbedEvent(timeCalculationStrategy.getProcessingTime(), this, producer, event.getConsumer())
        );
    }

    @Override
    public void onResourceGrabbed(ResourceGrabbedEvent event) {
        ResourceProducer producer = event.getProducer();
        ResourceConsumer consumer = event.getConsumer();
        resource = producer.produceResource();
        eventBus.registerEvent(
                new ConsumerApproachedEvent(RouteUtils.getInstance().getRouteLength(producer.getLocation(), consumer.getLocation()),
                        this, producer, consumer)
        );
    }

    @Override
    public void onConsumerApproached(ConsumerApproachedEvent event) {
        ResourceConsumer consumer = event.getConsumer();
        location = consumer.getLocation();
        eventBus.registerEvent(
                new ResourceDroppedEvent(timeCalculationStrategy.getProcessingTime(), this, event.getProducer(), consumer)
        );
    }

    @Override
    public void onResourceDropped(ResourceDroppedEvent event) {
        ResourceConsumer consumer = event.getConsumer();
        consumer.consumeResource(resource);
        resource = null;
        occupied = false;
        eventBus.registerEvent(new RobotReleasedEvent(0, this));
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Required
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
