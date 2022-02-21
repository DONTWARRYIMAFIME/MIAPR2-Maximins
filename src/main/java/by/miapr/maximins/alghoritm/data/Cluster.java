package by.miapr.maximins.alghoritm.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cluster implements Serializable {
    private final List<Point> points = new ArrayList<>();

    private double centerX;
    private double centerY;

    public Cluster(double centerX, double centerY) {
        setCenter(centerX, centerY);
    }

    public void setCenter(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

        clearPoints();
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void addPoint(Point point){
        points.add(point);
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public void clearPoints() {
        points.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cluster cluster = (Cluster) o;
        return Double.compare(cluster.centerX, centerX) == 0 && Double.compare(cluster.centerY, centerY) == 0 && Objects.equals(points, cluster.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, centerX, centerY);
    }
}