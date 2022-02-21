package by.miapr.maximins.alghoritm.randomizer;

import java.io.Serializable;

public interface Randomizer extends Serializable {
    static double randomDouble(double min, double max) {
        return min + (int)(Math.random() * (max - min + 1));
    }
}

