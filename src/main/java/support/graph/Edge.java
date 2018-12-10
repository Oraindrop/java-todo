package support.graph;

import kakaopay.domain.reference.Reference;

public class Edge {
    private Node sourceNode;
    private Node targetNode;

    public Edge(Reference reference) {
        this.sourceNode = new Node(reference.getSource().getId());
        this.targetNode = new Node(reference.getTarget().getId());
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public boolean isFromSourceNode(Node v) {
        return this.sourceNode.equals(v);
    }

    public boolean isToTargetNode(Node v) {
        return this.targetNode.equals(v);
    }

    @Override
    public String toString() {
        return "Edge(" +
                sourceNode + "->" +
                targetNode +
                ')';
    }
}
