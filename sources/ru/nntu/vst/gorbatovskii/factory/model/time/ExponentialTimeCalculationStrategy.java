package ru.nntu.vst.gorbatovskii.factory.model.time;

import org.springframework.beans.factory.annotation.Required;

import java.util.Random;

public class ExponentialTimeCalculationStrategy implements TimeCalculationStrategy {

    private Random random;
    private double average;

    public ExponentialTimeCalculationStrategy() {
        random = new Random();
    }

    /**
     * The processing algorithm copied from https://stackoverflow.com/questions/29020652/java-exponential-distribution
     * where lambda (according to http://statistica.ru/theory/eksponentsialnoe-raspredelenie/) = 1/average
     */
    @Override
    public double getProcessingTime() {
        return Math.log(1 - random.nextDouble()) / (-(1 / average));
    }

    @Required
    public void setAverage(double average) {
        this.average = average;
    }
}
