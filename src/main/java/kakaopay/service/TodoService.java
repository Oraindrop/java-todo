package kakaopay.service;

import kakaopay.domain.Todo;
import kakaopay.domain.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Page<Todo> findAllByCompleted(boolean bool, Pageable pageable) {
        return todoRepository.findAllByCompleted(bool, pageable);
    }

}
