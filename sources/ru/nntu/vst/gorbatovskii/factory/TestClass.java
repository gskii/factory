package ru.nntu.vst.gorbatovskii.factory;

import ru.nntu.vst.gorbatovskii.factory.events.EventBus;
import ru.nntu.vst.gorbatovskii.factory.events.stock.ResourceAppearedEvent;
import ru.nntu.vst.gorbatovskii.factory.model.common.Stock;
import ru.nntu.vst.gorbatovskii.factory.model.common.Storage;
import ru.nntu.vst.gorbatovskii.factory.utils.ApplicationContextUtils;

import java.math.BigInteger;

public class TestClass {
    public static void main(String[] args) {
        EventBus eventBus = (EventBus) ApplicationContextUtils.getApplicationContext().getBean("eventBus");
        Stock stock = (Stock) ApplicationContextUtils.getApplicationContext().getBean("stock");
        Storage storage = (Storage) ApplicationContextUtils.getApplicationContext().getBean("storage");
        eventBus.registerEvent(new ResourceAppearedEvent(0, stock));
        while (eventBus.hasNext()){
//        for (int i = 0; i < 100 && eventBus.hasNext(); i++) {
            eventBus.executeNext();
        }

        BigInteger breakBI = BigInteger.ONE;
    }
}
