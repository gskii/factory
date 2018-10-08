package ru.nntu.vst.gorbatovskii.factory.model.common;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.events.EventBus;
import ru.nntu.vst.gorbatovskii.factory.events.system.TerminateSystemEvent;
import ru.nntu.vst.gorbatovskii.factory.model.robot.Robot;
import ru.nntu.vst.gorbatovskii.factory.model.robot.RobotProvider;

import java.util.LinkedList;
import java.util.List;

public class Storage implements ResourceConsumer {

    private RobotProvider robotProvider;
    private ResourceProducer producer;
    private EventBus eventBus;
    private Location location;
    private int terminateResourceCount;

    private List<Resource> resources = new LinkedList<>();

    public void onResourceAppeared() {
        Robot robot = robotProvider.getNearestUnoccupiedRobot(this);
        robot.transferResource(producer, this);
    }

    @Override
    public void consumeResource(Resource resource) {
        resources.add(resource);
        if (resources.size() >= terminateResourceCount) {
            eventBus.registerEvent(new TerminateSystemEvent(0));
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Required
    public void setTerminateResourceCount(int terminateResourceCount) {
        this.terminateResourceCount = terminateResourceCount;
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
    public void setLocation(Location location) {
        this.location = location;
    }

    @Required
    public void setProducer(ResourceProducer producer) {
        this.producer = producer;
    }
}
