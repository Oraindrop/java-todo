package support.graph;

import java.util.Objects;

public class Node {
    private long id;

    private Node(long id) {
        this.id = id;
    }

    public static Node ofId(long id) {
        return new Node(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }


}
