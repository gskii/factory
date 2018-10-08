package ru.nntu.vst.gorbatovskii.factory.model.robot;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.common.Location;
import ru.nntu.vst.gorbatovskii.factory.model.robot.eligibility.RobotEligibilityStrategy;
import ru.nntu.vst.gorbatovskii.factory.utils.RouteUtils;

public class RobotProviderImpl implements RobotProvider {

    private RobotEligibilityStrategy eligibilityStrategy;

    public Robot getNearestUnoccupiedRobot(ResourceConsumer consumer) {
        final Location consumerLocation = consumer.getLocation();
        return eligibilityStrategy.getEligibleRobotsForConsumer(consumer).stream()
                .filter(robot -> !robot.isOccupied())
                .sorted((r1, r2) -> {
                    double r1Length = RouteUtils.getInstance().getRouteLength(consumerLocation, r1.getLocation());
                    double r2Length = RouteUtils.getInstance().getRouteLength(consumerLocation, r2.getLocation());
                    return Double.compare(r1Length, r2Length);
                }).findFirst().orElse(null);
    }

    @Required
    public void setEligibilityStrategy(RobotEligibilityStrategy eligibilityStrategy) {
        this.eligibilityStrategy = eligibilityStrategy;
    }
}
