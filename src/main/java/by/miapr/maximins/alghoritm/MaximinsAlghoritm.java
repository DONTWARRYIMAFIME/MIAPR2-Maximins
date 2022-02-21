package by.miapr.maximins.alghoritm;

import by.miapr.maximins.alghoritm.data.Cluster;
import by.miapr.maximins.alghoritm.data.Point;
import by.miapr.maximins.alghoritm.generator.impl.ClusterGenerator;
import by.miapr.maximins.alghoritm.generator.impl.PointGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class MaximinsAlghoritm {
    private final List<Cluster> clusters;
    private final List<Point> points;

    private final MaximinsAlghoritmConfig config;


    private int currentIteration;
    private boolean isCompleted;

    public MaximinsAlghoritm(MaximinsAlghoritmConfig config) {
        this.config = config;

        clusters = new ClusterGenerator().generate(1);
        points = new PointGenerator().generate(config.getNumberOfPoints());
    }

    public void iterate() {
        if (currentIteration == 0) {
            clusters.clear();
            clusters.add(initFistCluster(points));
        } else if (currentIteration == 1) {
            clusters.add(initSecondCluster(points, clusters.get(0).getCenterX(), clusters.get(0).getCenterX()));
        } else {
            Map<Point, Double> maxCenterDist = getMaxDistPoint();
            Map.Entry<Point, Double> entry = maxCenterDist.entrySet().stream()
                    .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get();

            double dist = getArDistanceBetweenCenters(entry.getKey());
            if (entry.getValue() <= dist / 2) {
                isCompleted = true;
                return;
            }

            clusters.add(new Cluster(entry.getKey().getX(), entry.getKey().getY()));
        }

        definePointsCluster(points);
        currentIteration++;
    }

    public void iterate(int amount) {
        IntStream.range(0, amount).forEach((ind) -> iterate());
    }

    public List<Cluster> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    private Cluster initFistCluster(List<Point> pointList) {
        int index = new Random().nextInt(pointList.size());

        Point centerPoint = pointList.get(index);
        return new Cluster(centerPoint.getX(), centerPoint.getY());
    }

    private Cluster initSecondCluster(List<Point> pointList, double centerX, double centerY) {
        Point point = findSecondPoint(pointList, centerX, centerY);
        return new Cluster(point.getX(), point.getY());
    }

    private Point findSecondPoint(List<Point> pointList, double centerX, double centerY) {
        final Point[] maxDistPoint = new Point[1];
        final double[] maxDistance = {-1};
        pointList.stream().forEach(point -> {
            double dist = calculateDistance(point.getX(), point.getY(), centerX, centerY);
            if (dist > maxDistance[0]) {
                maxDistance[0] = dist;
                maxDistPoint[0] = point;
            }
        });
        return maxDistPoint[0];
    }


    private void definePointCluster(Point point, List<Cluster> clusters) {
        Cluster neededCluster = null;
        double minDist = -1;

        for (Cluster cluster : clusters) {
            double dist = calculateDistance(cluster.getCenterX(), cluster.getCenterY(), point.getX(), point.getY());
            if (dist < minDist || minDist == -1) {
                minDist = dist;
                neededCluster = cluster;
            }
        }

        try {
            neededCluster.addPoint(point);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void definePointsCluster(List<Point> pointList) {

        clusters.forEach(Cluster::clearPoints);
        for (Point point : pointList) {
            definePointCluster(point, clusters);
        }
    }

    private void definePointsCluster(Point point, List<Cluster> clusters) {
        Cluster neededCluster = null;
        double minDist = -1;

        for (Cluster cluster : clusters) {
            double dist = calculateDistance(cluster.getCenterX(), cluster.getCenterY(), point.getX(), point.getY());
            if (dist < minDist || minDist == -1) {
                minDist = dist;
                neededCluster = cluster;
            }
        }
        try {
            neededCluster.addPoint(point);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Map<Point, Double> getMaxDistPoint() {

        Map<Point, Double> maxPointMap = new HashMap<>();

        clusters.forEach(cluster -> {

            AtomicReference<Double> maxDistance = new AtomicReference<>(0.0);
            AtomicReference<Point> maxDistPoint = new AtomicReference<>();

            cluster.getPoints().forEach(point -> {
                double dist = calculateDistance(cluster.getCenterX(), cluster.getCenterY(), point.getX(), point.getY());
                if (dist > maxDistance.get()) {
                    maxDistance.set(dist);
                    maxDistPoint.set(point);
                }
            });
            maxPointMap.put(maxDistPoint.get(), maxDistance.get());
        });
        return maxPointMap;
    }

    private Double getArDistanceBetweenCenters(Point point) {

        AtomicReference<Double> aSumX = new AtomicReference<>(0.0);
        AtomicReference<Double> aSumY = new AtomicReference<>(0.0);

        clusters.forEach(cluster -> {
            aSumX.updateAndGet(v -> v + cluster.getCenterX());
            aSumY.updateAndGet(v -> v + cluster.getCenterY());
        });

        double sumX = aSumX.get() / clusters.size();
        double sumY = aSumY.get() / clusters.size();

        return calculateDistance(sumX, sumY, point.getX(), point.getY());
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}