package kakaopay.domain;

import kakaopay.validate.ReferenceGraph;
import org.slf4j.Logger;
import support.domain.AbstractEntity;
import support.graph.Edge;
import support.graph.Graph;
import support.graph.Node;
import support.util.ContentsParser;

import javax.persistence.Entity;
import javax.persistence.Lob;

import static org.slf4j.LoggerFactory.getLogger;

@Entity
public class Todo extends AbstractEntity {
    private static final Logger logger = getLogger(Todo.class);

    @Lob
    private String contents;

    private boolean completed;

    public Todo() {
        this.completed = false;
    }

    public Todo(String contents) {
        this.completed = false;
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void createReference() {
        ReferenceGraph.addReference(this.makeReferenceGraph());
    }

    public Graph makeReferenceGraph() {
        Node selfNode = Node.ofId(getId());
        Graph theGraph = Graph.of();
        for (Long id : ContentsParser.parseReference(contents)) {
            logger.debug("Reference {} -> {}", selfNode, id);
            theGraph.add(Edge.ofNodes(selfNode, Node.ofId(id)));
        }
        return theGraph;
    }

    public void update(String newContents) {
        if (isPossibleUpdate(newContents)) {
            ReferenceGraph.deleteReference(getId());
            ReferenceGraph.addReference(this.makeReferenceGraph(newContents));
            this.contents = newContents;
        }
    }

    private boolean isPossibleUpdate(String newContents) {
        // 참조 변경사항 없는 case.
        if (ContentsParser.parseReference(newContents).equals(ContentsParser.parseReference(this.contents)))
            return true;
        // Cycle 여부 확인.
        return ReferenceGraph.isCycle(getId(), makeReferenceGraph(newContents));
    }

    private Graph makeReferenceGraph(String newContents) {
        Node selfNode = Node.ofId(getId());
        Graph theGraph = Graph.of();
        for (Long id : ContentsParser.parseReference(newContents)) {
            logger.debug("Reference {} -> {}", selfNode, id);
            theGraph.add(Edge.ofNodes(selfNode, Node.ofId(id)));
        }
        return theGraph;
    }

    public void complete() {
        ReferenceGraph.deleteReference(getId());
        this.completed = true;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "contents='" + contents + '\'' +
                ", completed=" + completed +
                '}';
    }
}
