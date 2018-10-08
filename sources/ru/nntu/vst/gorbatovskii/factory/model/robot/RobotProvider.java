package ru.nntu.vst.gorbatovskii.factory.model.robot;

import ru.nntu.vst.gorbatovskii.factory.model.common.ResourceConsumer;

public interface RobotProvider {

    Robot getNearestUnoccupiedRobot(ResourceConsumer consumer);
}
