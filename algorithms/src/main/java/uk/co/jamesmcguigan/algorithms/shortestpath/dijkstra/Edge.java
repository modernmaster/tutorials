package uk.co.jamesmcguigan.algorithms.shortestpath.dijkstra;

class Edge {
    private final Vertex target;
    private final double weight;

    Edge(final Vertex argTarget, final double argWeight) {
        target = argTarget;
        weight = argWeight;
    }

    public Vertex getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }
}
