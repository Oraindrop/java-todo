package kakaopay.validate;

import kakaopay.domain.reference.Reference;
import org.slf4j.Logger;
import support.graph.Edge;
import support.graph.Node;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class CycleDetector {
    private static final Logger logger = getLogger(CycleDetector.class);

    private List<Edge> graph;

    public CycleDetector(List<Reference> references) {
        this.graph = new ArrayList<>();
        for (Reference reference : references) {
            this.graph.add(new Edge(reference));
        }
    }

    public void detectCycle() {
        Set<Node> sources = makeSources();
        for (Node source : sources) {
            start(source);
        }
    }

    private Set<Node> makeSources() {
        Set<Node> sources = new HashSet<>();
        for (Edge edge : this.graph) {
            sources.add(edge.getSourceNode());
        }
        return sources;
    }

    private void start(Node source) {
        for (Edge edge : this.graph) {
            if (edge.isFromSourceNode(source)) dfs(edge.getTargetNode(), source);
        }
    }

    private void dfs(Node v, Node here) {
        logger.debug("Node : {} start", v);
        Stack<Node> stack = new Stack<>();
        stack.push(v);
        List<Node> visited = new ArrayList<>();

        while (!stack.empty()) {
            Node source = stack.pop();
            if (source.equals(here)) throw new IllegalArgumentException("사이클이 존재하여 수정할 수 없습니다. : " + here);
            visited.add(source);

            search(stack, visited, source);
        }
    }

    private void search(Stack<Node> stack, List<Node> visited, Node source) {
        for (Edge edge : this.graph) {
            if (edge.isFromSourceNode(source) && !visited.contains(edge.getTargetNode())) {
                stack.push(edge.getTargetNode());
            }
        }
    }
}
