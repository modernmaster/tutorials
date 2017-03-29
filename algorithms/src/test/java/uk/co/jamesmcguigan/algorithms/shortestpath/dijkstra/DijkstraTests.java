package uk.co.jamesmcguigan.algorithms.shortestpath.dijkstra;

import org.junit.Test;

import java.util.List;

/**
 * Created by James McGuigan on 01/03/2015.
 */
public class DijkstraTests {
    @Test
    public void DoSomething() {
        Vertex v0 = new Vertex("Redvile");
        Vertex v1 = new Vertex("Blueville");
        Vertex v2 = new Vertex("Greenville");
        Vertex v3 = new Vertex("Orangeville");
        Vertex v4 = new Vertex("Purpleville");

        v0.adjacencies = new Edge[]{new Edge(v1, 5),
                new Edge(v2, 10),
                new Edge(v3, 8)};
        v1.adjacencies = new Edge[]{new Edge(v0, 5),
                new Edge(v2, 3),
                new Edge(v4, 7)};
        v2.adjacencies = new Edge[]{new Edge(v0, 10),
                new Edge(v1, 3)};
        v3.adjacencies = new Edge[]{new Edge(v0, 8),
                new Edge(v4, 2)};
        v4.adjacencies = new Edge[]{new Edge(v1, 7),
                new Edge(v3, 2),
                new Edge(v0, 2)};


        Vertex[] vertices = {v0, v1, v2, v3, v4};

        Dijkstra d = new Dijkstra();
        d.computePaths(v0);
        for (Vertex v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            List<Vertex> path = d.getShortestPathTo(v);
            System.out.println("Path: " + path);
        }
    }
}
