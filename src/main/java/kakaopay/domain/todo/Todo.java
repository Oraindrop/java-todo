package kakaopay.domain.todo;

import org.slf4j.Logger;
import support.domain.AbstractEntity;
import support.util.ContentsParser;

import javax.persistence.Entity;
import javax.persistence.Lob;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Entity
public class
Todo extends AbstractEntity {
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

    public List<Long> makeReferences() {
        return ContentsParser.parseReference(this.contents);
    }


    public void update(String newContents) {
        this.contents = newContents;
    }

    public void complete() {
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
