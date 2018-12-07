package kakaopay.domain;

import support.domain.AbstractEntity;

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

    public void update(String contents){
        this.contents = contents;
    }

}
