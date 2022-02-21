package by.miapr.maximins.alghoritm;

public class MaximinsAlghoritmConfig {
    private int numberOfPoints = 10_000;

    public MaximinsAlghoritmConfig() {
    }

    public MaximinsAlghoritmConfig(int numberOfPoints, int numberOfClusters, int numberOfIterations) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
}
