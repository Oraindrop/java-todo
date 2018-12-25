package kakaopay.validate;

import kakaopay.domain.reference.Reference;
import org.slf4j.Logger;
import support.graph.Edge;
import support.graph.Node;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class CycleDetector {
    private static final Logger logger = getLogger(CycleDetector.class);

    public static void detectCycle(List<Reference> references) {
        List<Edge> graph = initGraph(references);
        Set<Node> sources = makeSources(graph);
        for (Node source : sources) {
            start(source, graph);
        }
    }

    private static List<Edge> initGraph(List<Reference> references){
        List<Edge> graph = new ArrayList<>();
        for (Reference reference : references) {
            graph.add(new Edge(reference));
        }
        return graph;
    }

    private static Set<Node> makeSources(List<Edge> graph) {
        Set<Node> sources = new HashSet<>();
        for (Edge edge : graph) {
            sources.add(edge.getSourceNode());
        }
        return sources;
    }

    private static void start(Node source, List<Edge> graph) {
        for (Edge edge : graph) {
            if (edge.isFromSourceNode(source)) dfs(edge.getTargetNode(), source, graph);
        }
    }

    private static void dfs(Node v, Node here, List<Edge> graph) {
        logger.debug("Node : {} start", v);
        Stack<Node> stack = new Stack<>();
        stack.push(v);
        List<Node> visited = new ArrayList<>();

        while (!stack.empty()) {
            Node source = stack.pop();
            if (source.equals(here)) throw new IllegalArgumentException("사이클이 존재하여 수정할 수 없습니다. : " + here);
            visited.add(source);

            search(stack, visited, source, graph);
        }
    }

    private static void search(Stack<Node> stack, List<Node> visited, Node source, List<Edge> graph) {
        for (Edge edge : graph) {
            if (edge.isFromSourceNode(source) && !visited.contains(edge.getTargetNode())) {
                stack.push(edge.getTargetNode());
            }
        }
    }
}
