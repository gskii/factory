package ru.nntu.vst.gorbatovskii.factory.model.robot.eligibility;

import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;
import ru.nntu.vst.gorbatovskii.factory.model.robot.Robot;

import java.util.List;

public interface RobotEligibilityStrategy {

    List<Robot> getEligibleRobotsForConsumer(ResourceConsumer consumer);
}
