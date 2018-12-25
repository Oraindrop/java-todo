package kakaopay.domain.todo;

import org.slf4j.Logger;
import support.domain.AbstractEntity;
import support.util.ContentsParser;

import javax.persistence.Entity;
import javax.persistence.Lob;

import java.util.List;
import java.util.Objects;

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

    public boolean isCompleted() {
        return completed;
    }

    public List<Long> extractTargetReferences() {
        return ContentsParser.parseReference(this.contents);
    }

    public void update(String newContents) {
        this.contents = newContents;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Todo todo = (Todo) o;
        return completed == todo.completed &&
                Objects.equals(contents, todo.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contents, completed);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "contents='" + contents + '\'' +
                ", completed=" + completed +
                '}';
    }
}
