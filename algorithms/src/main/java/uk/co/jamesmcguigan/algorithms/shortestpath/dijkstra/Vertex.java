package uk.co.jamesmcguigan.algorithms.shortestpath.dijkstra;

class Vertex implements Comparable<Vertex> {
    private final String name;
    private Edge[] adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous;

    Vertex(final String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(final Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(final double minDistance) {
        this.minDistance = minDistance;
    }

    public Edge[] getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(final Edge[] adjacencies) {
        this.adjacencies = adjacencies;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(final Vertex previous) {
        this.previous = previous;
    }
}
