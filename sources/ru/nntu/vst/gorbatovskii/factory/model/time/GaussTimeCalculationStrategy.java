package ru.nntu.vst.gorbatovskii.factory.model.time;

import org.springframework.beans.factory.annotation.Required;

import java.util.Random;

public class GaussTimeCalculationStrategy implements TimeCalculationStrategy {

    private double mean;
    private double standardDeviation;

    @Override
    public double getProcessingTime() {
        return new Random().nextGaussian() * standardDeviation + mean;
    }

    @Required
    public void setMean(double mean) {
        this.mean = mean;
    }

    @Required
    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
