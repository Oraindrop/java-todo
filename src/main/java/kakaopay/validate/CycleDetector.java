package kakaopay.validate;

import kakaopay.CanNotReferenceException;
import org.slf4j.Logger;
import support.graph.Edge;
import support.graph.Graph;
import support.graph.Node;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class CycleDetector {
    private static final Logger logger = getLogger(CycleDetector.class);

    private Graph aGraph;

    private CycleDetector(Graph aGraph) {
        this.aGraph = aGraph;
    }

    public static CycleDetector ofGraph(Graph g) {
        return new CycleDetector(g);
    }

    private Set<Node> makeSources() {
        Set<Node> sources = new HashSet<>();
        for (Edge edge : aGraph.getEdges()) {
            sources.add(edge.getSourceNode());
        }
        return sources;
    }

    public void detectCycle() {
        Set<Node> sources = makeSources();

        for (Node source : sources) {
            start(source);
        }
    }

    private void start(Node source) {
        for (Edge edge : aGraph.getEdges()) {
            if (edge.isFromSourceNode(source)) dfs(edge.getTargetNode(), source);
        }
    }

    private void dfs(Node v, Node here) throws CanNotReferenceException {
        logger.debug("Node : {} start", v);
        Stack<Node> stack = new Stack<>();
        stack.push(v);
        List<Node> visited = new ArrayList<>();

        while (!stack.empty()) {
            Node source = stack.pop();
            if (source.equals(here)) throw new CanNotReferenceException("사이클이 존재하여 수정할 수 없습니다. : " + here);
            visited.add(source);

            for (Edge edge : aGraph.getEdges()) {
                if (edge.isFromSourceNode(source) && !visited.contains(edge.getTargetNode())) {
                    stack.push(edge.getTargetNode());
                }
                ;
            }
        }
    }
}
