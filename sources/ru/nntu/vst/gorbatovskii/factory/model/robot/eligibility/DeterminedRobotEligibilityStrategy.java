package ru.nntu.vst.gorbatovskii.factory.model.robot.eligibility;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.Location;
import ru.nntu.vst.gorbatovskii.factory.model.robot.Robot;

import java.util.List;
import java.util.Map;

/**
 * Robot eligibility is specified for each consumer location
 */
public class DeterminedRobotEligibilityStrategy implements RobotEligibilityStrategy {

    private Map<Location, List<Robot>> robotEligibilityMap;

    public List<Robot> getEligibleRobotsForConsumer(ResourceConsumer consumer) {
        return robotEligibilityMap.get(consumer.getLocation());
    }

    @Required
    public void setRobotEligibilityMap(Map<Location, List<Robot>> robotEligibilityMap) {
        this.robotEligibilityMap = robotEligibilityMap;
    }
}
