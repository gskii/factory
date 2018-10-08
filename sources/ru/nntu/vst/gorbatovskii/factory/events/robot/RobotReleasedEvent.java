package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.GlobalEvent;

public class RobotReleasedEvent extends GlobalEvent<RobotReleasedHandler> {

    public RobotReleasedEvent(double duration) {
        super(duration, RobotReleasedHandler.class);
    }

    @Override
    protected void handle() {
        for (RobotReleasedHandler handler : getHandlers()) {
            handler.onRobotReleased(this);
        }
    }
}
