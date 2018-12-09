package kakaopay.validate;

import kakaopay.CanNotReferenceException;
import org.slf4j.Logger;
import support.graph.Edge;
import support.graph.Graph;
import support.graph.Node;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ReferenceGraph {
    private static final Logger logger = getLogger(ReferenceGraph.class);

    private static Graph theGraph = Graph.of();

    //create 시 addReference 가능
    public static void addReference(Graph g) {
        theGraph.add(g);
        logger.debug("ReferenceGraph : {}", theGraph);
    }

    //complete 시 자신이 target 에 없으면 가능
    //자신을 소스로 한 edge 를 전부 삭제하고 사라진다
    public static void deleteReference(long id) {
        Node v = Node.ofId(id);
        isDeletePossible(v);
        List<Edge> removeItems = new ArrayList<>();
        for (Edge edge : theGraph.getEdges()) {
            if (edge.isFromSourceNode(v)) removeItems.add(edge);
        }
        for (Edge removeItem : removeItems) {
            theGraph.remove(removeItem);
        }
        logger.debug("ReferenceGraph : {}", theGraph);
    }

    private static boolean isDeletePossible(Node v) {
        for (Edge edge : theGraph.getEdges()) {
            if (edge.isToTargetNode(v)) throw new CanNotReferenceException("참조하는 Todo가 존재하여 완료할 수 없습니다. : " + edge.getSourceNode());
        }
        return true;
    }

    public static boolean isCycle(long id, Graph g) {
        Graph tempGraph = Graph.ofGraph(theGraph);
        Node sourceNode = Node.ofId(id);
        logger.debug("tempGraph : {} ", tempGraph);
        logger.debug("sourceNode : {} ", sourceNode);

        tempGraph.removeWithSourceNode(sourceNode);
        tempGraph.add(g);
        CycleDetector cycleDetector = CycleDetector.ofGraph(tempGraph);
        cycleDetector.detectCycle();
        return true;
    }


}
