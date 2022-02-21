package by.miapr.maximins.alghoritm.generator.impl;

import by.miapr.maximins.alghoritm.data.Cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ClusterGenerator extends AbstractGenerator<Cluster> {
    @Override
    public Cluster generate() {
        return new Cluster(randomizeX(), randomizeY());
    }

    @Override
    public List<Cluster> generate(int amount) {
        List<Cluster> clusters = new ArrayList<>(amount);

        IntStream.range(0, amount)
                .forEach((id) -> clusters.add(generate()));

        return clusters;
    }
}
