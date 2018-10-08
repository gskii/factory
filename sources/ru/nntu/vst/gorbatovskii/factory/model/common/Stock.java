package ru.nntu.vst.gorbatovskii.factory.model.common;

import org.springframework.beans.factory.annotation.Required;

import java.util.LinkedList;

public class Stock implements ResourceProducer {

    private Location location;
    private LinkedList<Resource> resources;

    public Stock() {
        resources = new LinkedList<>();
    }

    @Override
    public Resource produceResource() {
        return resources.pop();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Required
    public void setLocation(Location location) {
        this.location = location;
    }
}
