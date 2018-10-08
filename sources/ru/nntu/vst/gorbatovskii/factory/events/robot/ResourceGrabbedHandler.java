package ru.nntu.vst.gorbatovskii.factory.events.robot;

import ru.nntu.vst.gorbatovskii.factory.events.Handler;

public interface ResourceGrabbedHandler extends Handler {

    void onResourceGrabbed(ResourceGrabbedEvent event);
}
