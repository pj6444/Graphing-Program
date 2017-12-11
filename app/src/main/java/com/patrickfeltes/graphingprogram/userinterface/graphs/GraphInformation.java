package com.patrickfeltes.graphingprogram.userinterface.graphs;

public class GraphInformation {
    private double minX;
    private double maxX;
    private int partitions;
    private String equation;

    public GraphInformation(double minX, double maxX, int partitions, String equation) {
        this.minX = minX;
        this.maxX = maxX;
        this.partitions = partitions;
        this.equation = equation;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public int getPartitions() {
        return partitions;
    }

    public String getEquation() {
        return equation;
    }
}
