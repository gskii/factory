package ru.nntu.vst.gorbatovskii.factory;

import ru.nntu.vst.gorbatovskii.factory.events.EventBus;
import ru.nntu.vst.gorbatovskii.factory.events.stock.ResourceAppearedEvent;
import ru.nntu.vst.gorbatovskii.factory.model.common.Stock;
import ru.nntu.vst.gorbatovskii.factory.utils.ApplicationContextUtils;

public class TestClass {
    public static void main(String[] args) {
        EventBus eventBus = (EventBus) ApplicationContextUtils.getApplicationContext().getBean("eventBus");
        Stock stock = (Stock) ApplicationContextUtils.getApplicationContext().getBean("stock");
        eventBus.registerEvent(new ResourceAppearedEvent(0, stock));
        while (eventBus.hasNext()) {
            eventBus.executeNext();
        }
    }
}
