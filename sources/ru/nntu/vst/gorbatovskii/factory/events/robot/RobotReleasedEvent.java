package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.GlobalEvent;
import ru.nntu.vst.gorbatovskii.factory.model.robot.Robot;

public class RobotReleasedEvent extends GlobalEvent<RobotReleasedHandler> {

    private Robot robot;

    public RobotReleasedEvent(double duration, Robot robot) {
        super(duration, RobotReleasedHandler.class);
        this.robot = robot;
    }

    @Override
    protected void handle() {
        for (RobotReleasedHandler handler : getHandlers()) {
            handler.onRobotReleased(this);
        }
    }

    @Override
    public String toString() {
        return robot.toString() + ".RobotReleasedEvent";
    }
}
