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

    // Next resource consumer specified in Spring configuration
    // For Machine it can't be null
    private ResourceConsumer nextResourceConsumer;

    // Producer that produced new resource and is waiting
    // until current consumer hands over processed resource
    // and finds robot to deliver new resource
    private ResourceProducer readyProducer;

    private Location location;
    private Resource resource;

    @Override
    public void consumeResource(Resource resource) {
        this.resource = resource;
        eventBus.registerEvent(
                new ResourceProcessedEvent(timeCalculationStrategy.getProcessingTime(), this)
        );
    }

    @Override
    public Resource produceResource() {
        Resource resourceToProduce = resource;
        resource = null;
        return resourceToProduce;
    }

    @Override
    public void onResourceAppeared(ResourceProducer producer) {
        readyProducer = producer;
        if (resource == null) {
            Robot robot = robotProvider.getNearestUnoccupiedRobot(this);
            if (robot != null) {
                readyProducer = null;
                robot.transferResource(producer, this);
            }
        }
    }

    @Override
    public void onResourceProcessed() {
        nextResourceConsumer.onResourceAppeared(this);
    }

    @Override
    public void onRobotReleased(RobotReleasedEvent event) {
        if (readyProducer != null) {
            onResourceAppeared(readyProducer);
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Required
    public void setLocation(Location location) {
        this.location = location;
    }

    @Required
    public void setNextResourceConsumer(ResourceConsumer nextResourceConsumer) {
        this.nextResourceConsumer = nextResourceConsumer;
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
