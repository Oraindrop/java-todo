package kakaopay.validate;

import kakaopay.CanNotReferenceException;
import org.junit.Test;
import support.graph.Edge;
import support.graph.Graph;
import support.graph.Node;

import static org.junit.Assert.*;

public class ReferenceGraphTest {

    @Test
    public void addReference() {
        Graph graph = Graph.of();
        graph.add(Edge.ofNodes(Node.ofId(1), Node.ofId(2)));
        ReferenceGraph.addReference(graph);
    }

    @Test
    public void deleteReference() {
        Graph graph = Graph.of();
        graph.add(Edge.ofNodes(Node.ofId(1), Node.ofId(2)));
        ReferenceGraph.addReference(graph);
        ReferenceGraph.deleteReference(1);
    }

    @Test(expected = CanNotReferenceException.class)
    public void deleteReferenceWithException() {
        Graph graph = Graph.of();
        graph.add(Edge.ofNodes(Node.ofId(1), Node.ofId(2)));
        ReferenceGraph.addReference(graph);
        ReferenceGraph.deleteReference(2);
    }

    @Test
    public void isCycle() {
    }
}