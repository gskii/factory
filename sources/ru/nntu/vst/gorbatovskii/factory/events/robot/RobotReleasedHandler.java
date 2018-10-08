package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.Handler;

public interface RobotReleasedHandler extends Handler{

    void onRobotReleased(RobotReleasedEvent event);
}
