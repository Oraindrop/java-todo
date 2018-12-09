package support.graph;

public class Edge {
    private Node sourceNode;
    private Node targetNode;

    private Edge(Node sourceNode, Node targetNode) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    public static Edge ofNodes(Node sourceNode, Node targetNode) {
        return new Edge(sourceNode, targetNode);
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
