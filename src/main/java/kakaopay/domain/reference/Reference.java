package kakaopay.domain.reference;

import kakaopay.domain.todo.Todo;
import support.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Reference extends AbstractEntity {
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_source_todo"))
    private Todo source;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_target_todo"))
    private Todo target;

    public Reference() {
    }

    public Reference(Todo source, Todo target) {
        this.checkSelfReference(source, target);
        this.source = source;
        this.target = target;
    }

    public Todo getSource() {
        return source;
    }

    public Todo getTarget() {
        return target;
    }

    private void checkSelfReference(Todo source, Todo target) {
        if (source.equals(target))
            throw new IllegalArgumentException("자기 자신은 참조할 수 없습니다 : " + source.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference that = (Reference) o;
        return Objects.equals(source, that.source) &&
                Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return "Reference{" +
                source +
                " -> "
                + target +
                '}';
    }
}
