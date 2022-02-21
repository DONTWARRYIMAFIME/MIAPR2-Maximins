package by.miapr.maximins.alghoritm.generator.impl;

import by.miapr.maximins.alghoritm.data.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PointGenerator extends AbstractGenerator<Point> {
    @Override
    public Point generate() {
        return new Point(randomizeX(), randomizeY());
    }

    @Override
    public List<Point> generate(int amount) {
        List<Point> points = new ArrayList<>(amount);

        IntStream.range(0, amount)
                .forEach((id) -> points.add(generate()));

        return points;
    }
}