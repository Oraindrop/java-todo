package kakaopay.domain.todo;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TodoTest {
    @Test
    public void equals() {
        Todo todo = new Todo();
        Todo other = new Todo();
        todo.setId(1);
        other.setId(1);
        assertThat(todo.equals(other)).isTrue();

        Todo another = new Todo();
        another.setId(3);
        assertThat(todo.equals(another)).isFalse();
    }
}