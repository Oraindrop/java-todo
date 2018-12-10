package kakaopay.domain.reference;

import kakaopay.domain.todo.Todo;
import org.junit.Before;
import org.junit.Test;

import java.sql.Ref;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ReferenceTest {
    Todo source;
    Todo target;
    Todo fake;

    @Before
    public void setUp() throws Exception {
        source = new Todo("hello");
        source.setId(1);
        target = new Todo("hello");
        target.setId(1);
        fake = new Todo("hello");
        fake.setId(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithException() {
        Reference reference = new Reference(source, target);
    }

    @Test
    public void create() {
        Reference reference = new Reference(source, fake);
        Reference other = new Reference(fake, target);
        assertThat(reference.equals(other)).isFalse();
    }
}