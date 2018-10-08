package ru.nntu.vst.gorbatovskii.factory.events.system;

import org.springframework.beans.factory.annotation.Required;

public class ExitProgramSystemHandler implements TerminateSystemHandler {

    private int order;

    @Override
    public void onSystemTerminate(TerminateSystemEvent event) {
        System.exit(0);
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Required
    public void setOrder(int order) {
        this.order = order;
    }
}
