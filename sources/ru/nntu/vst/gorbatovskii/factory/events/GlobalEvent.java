package ru.nntu.vst.gorbatovskii.factory.events;

import ru.nntu.vst.gorbatovskii.factory.utils.ApplicationContextUtils;

import java.util.Collection;

public abstract class GlobalEvent<H> extends Event {

    private Class<H> typeParameterClass;

    public GlobalEvent(double duration, Class<H> typeParameterClass) {
        super(duration);
        this.typeParameterClass = typeParameterClass;
    }

    protected Collection<H> getHandlers() {
        return ApplicationContextUtils.getApplicationContext().getBeansOfType(typeParameterClass).values();
    }
}
