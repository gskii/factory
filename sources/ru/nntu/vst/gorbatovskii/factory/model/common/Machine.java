package ru.nntu.vst.gorbatovskii.factory.model.common;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.events.EventBus;
import ru.nntu.vst.gorbatovskii.factory.events.machine.ResourceProcessedEvent;
import ru.nntu.vst.gorbatovskii.factory.events.machine.ResourceProcessedHandler;
import ru.nntu.vst.gorbatovskii.factory.events.robot.RobotReleasedEvent;
import ru.nntu.vst.gorbatovskii.factory.events.robot.RobotReleasedHandler;
import ru.nntu.vst.gorbatovskii.factory.model.robot.Robot;
import ru.nntu.vst.gorbatovskii.factory.model.robot.RobotProvider;
import ru.nntu.vst.gorbatovskii.factory.model.time.TimeCalculationStrategy;

public class Machine implements ResourceConsumer, ResourceProducer, ResourceProcessedHandler, RobotReleasedHandler {

    private RobotProvider robotProvider;
    private EventBus eventBus;
    private TimeCalculationStrategy timeCalculationStrategy;

    // Next resource consumer and previous resource producer specified in Spring configuration/
    // For Machine both of them can't be null
    private ResourceConsumer consumer;
    private ResourceProducer producer;

    private Location location;
    private Resource resource;

    private boolean transferInProgress;

    @Override
    public void consumeResource(Resource resource) {
        this.resource = resource;
        transferInProgress = false;
        eventBus.registerEvent(
                new ResourceProcessedEvent(timeCalculationStrategy.getProcessingTime(), this)
        );
    }

    @Override
    public Resource produceResource() {
        Resource resourceToProduce = resource;
        resource = null;
        requestResource();
        return resourceToProduce;
    }

    @Override
    public void onResourceAppeared() {
        requestResource();
    }

    @Override
    public void onRobotReleased(RobotReleasedEvent event) {
        requestResource();
    }

    @Override
    public void onResourceProcessed() {
        consumer.onResourceAppeared();
    }

    private void requestResource() {
        if (resource == null && !transferInProgress && producer.hasResources()) {
            Robot robot = robotProvider.getNearestUnoccupiedRobot(this);
            if (robot != null) {
                transferInProgress = true;
                robot.transferResource(producer, this);
            }
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean hasResources() {
        return resource != null;
    }

    @Required
    public void setLocation(Location location) {
        this.location = location;
    }

    @Required
    public void setConsumer(ResourceConsumer consumer) {
        this.consumer = consumer;
    }

    @Required
    public void setProducer(ResourceProducer producer) {
        this.producer = producer;
    }

    @Required
    public void setRobotProvider(RobotProvider robotProvider) {
        this.robotProvider = robotProvider;
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
