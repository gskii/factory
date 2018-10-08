package ru.nntu.vst.gorbatovskii.factory.model.time;

import org.springframework.beans.factory.annotation.Required;

import java.util.Random;

public class RangeTimeCalculationStrategy implements TimeCalculationStrategy {

    private int average;
    private int averageVariation;

    @Override
    public double getProcessingTime() {
        return average + new Random().nextDouble() * averageVariation;
    }

    @Required
    public void setAverage(int average) {
        this.average = average;
    }

    @Required
    public void setAverageVariation(int averageVariation) {
        this.averageVariation = averageVariation;
    }
}
