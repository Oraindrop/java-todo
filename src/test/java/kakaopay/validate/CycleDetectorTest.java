package kakaopay.validate;

import kakaopay.domain.reference.Reference;
import kakaopay.domain.todo.Todo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CycleDetectorTest {
    List<Reference> references = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        references.add(new Reference(create(1), create(2)));
        references.add(new Reference(create(1), create(5)));
        references.add(new Reference(create(2), create(3)));
        references.add(new Reference(create(3), create(4)));
        // 1->2->3->4 , 1->5
    }

    public Todo create(long id){
        Todo aTodo = new Todo();
        aTodo.setId(id);
        return aTodo;
    }

    @Test
    public void detectCycle() {
        //without exception.
        CycleDetector detector = new CycleDetector(references);
        detector.detectCycle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void detectCycleWithException() {
        // 1->2->3->4 , 1->5
        references.add(new Reference(create(4), create(2)));
        // 2->3->4->2->3->4->2 ... create cycle
        CycleDetector detector = new CycleDetector(references);
        detector.detectCycle();
    }


}