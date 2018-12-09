package support.graph;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class Graph {
    private static final Logger logger = getLogger(Graph.class);

    private List<Edge> edges;

    private Graph() {
        this.edges = new ArrayList<>();
    }

    private Graph(List<Edge> edges) {
        this.edges = edges;
    }

    public static Graph of() {
        return new Graph();
    }

    public static Graph ofGraph(Graph g) {
        return new Graph(new ArrayList<>(g.edges));
    }

    public void add(Edge e) {
        this.edges.add(e);
    }

    public void add(Graph g) {
        this.edges.addAll(new ArrayList<>(g.edges));
    }

    public boolean remove(Edge e) {
        return this.edges.remove(e);
    }

    public boolean removeWithSourceNode(Node v) {
        logger.debug("v : {}", v);
        logger.debug("edges : {}", edges);
        List<Edge> removeItems = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.isFromSourceNode(v)) removeItems.add(edge);
        }
        for (Edge removeItem : removeItems) {
            edges.remove(removeItem);
        }
        return true;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Graph{" +
                edges +
                '}';
    }
}
