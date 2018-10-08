package ru.nntu.vst.gorbatovskii.factory.events.system;

import ru.nntu.vst.gorbatovskii.factory.events.Handler;

public interface TerminateSystemHandler extends Handler {

    void onSystemTerminate(TerminateSystemEvent event);
}
