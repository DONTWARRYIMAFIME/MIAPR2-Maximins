package by.miapr.maximins.alghoritm.generator;

import java.io.Serializable;
import java.util.List;

public interface Generator<T> extends Serializable {
    T generate();
    List<T> generate(int amount);
}