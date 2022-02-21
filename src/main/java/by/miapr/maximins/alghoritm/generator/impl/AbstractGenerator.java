package by.miapr.maximins.alghoritm.generator.impl;

import by.miapr.maximins.alghoritm.generator.Generator;
import by.miapr.maximins.alghoritm.randomizer.Randomizer;

public abstract class AbstractGenerator<T> implements Generator<T> {
    protected double minX = 0;
    protected double maxX = 500;

    protected double minY = 0;
    protected double maxY = 500;

    protected double randomizeX() {
        return Randomizer.randomDouble(minX, maxX);
    }

    protected double randomizeY() {
        return Randomizer.randomDouble(minY, maxY);
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
}