package kakaopay.todo;

import kakaopay.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Todo extends AbstractEntity {
    @Lob
    private String contents;

    private boolean completed;

    public Todo() {
        this.completed = false;
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


}
