package kakaopay.todo;

import kakaopay.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Todo extends AbstractEntity {
    @Lob
    private String contents;

    private boolean deleted;

    public Todo() {
        this.deleted = false;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


}
