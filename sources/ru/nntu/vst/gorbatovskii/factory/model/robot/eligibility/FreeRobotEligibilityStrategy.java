package ru.nntu.vst.gorbatovskii.factory.model.robot.eligibility;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.robot.Robot;

import java.util.List;

/**
 * Undetermined strategy, all robots are eligible for all consumers
 */
public class FreeRobotEligibilityStrategy implements RobotEligibilityStrategy {

    private List<Robot> robots;

    public List<Robot> getEligibleRobotsForConsumer(ResourceConsumer consumer) {
        return robots;
    }

    @Required
    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }
}
