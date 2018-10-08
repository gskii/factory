package ru.nntu.vst.gorbatovskii.factory.model.common;

import org.springframework.beans.factory.annotation.Required;

public class Route {
    private Location pointA;
    private Location pointB;
    private double weight;

    public Location getPointA() {
        return pointA;
    }

    public Location getPointB() {
        return pointB;
    }

    public double getWeight() {
        return weight;
    }

    @Required
    public void setPointA(Location pointA) {
        this.pointA = pointA;
    }

    @Required
    public void setPointB(Location pointB) {
        this.pointB = pointB;
    }

    @Required
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Route{" + pointA +
                ", " + pointB +
                ", " + weight +
                '}';
    }
}
