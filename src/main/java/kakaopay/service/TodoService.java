package kakaopay.service;

import kakaopay.CanNotReferenceException;
import kakaopay.domain.Todo;
import kakaopay.domain.TodoRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import support.util.ContentsParser;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class TodoService {
    private static final Logger logger = getLogger(TodoService.class);

    @Autowired
    private TodoRepository todoRepository;

    public Page<Todo> findAllByCompleted(boolean bool, Pageable pageable) {
        return todoRepository.findAllByCompleted(bool, pageable);
    }

    public Todo create(String contents) {
        Todo theTodo = todoRepository.save(new Todo(checkExistReference(contents)));
        theTodo.createReference();
        logger.debug("createTodo : {}", theTodo);
        return theTodo;
    }

    public Todo update(long id, String contents) {
        Todo theTodo = todoRepository.findByIdAndCompleted(id, false).orElseThrow(IllegalStateException::new);
        theTodo.update(checkSelfReference(id, checkExistReference(contents)));
        logger.debug("updateTodo : {}", theTodo);
        return todoRepository.save(theTodo);
    }

    public Todo delete(long id) {
        Todo theTodo = todoRepository.findByIdAndCompleted(id, false).orElseThrow(IllegalStateException::new);
        theTodo.complete();
        logger.debug("completeTodo : {}", theTodo);
        return todoRepository.save(theTodo);
    }

    private String checkExistReference(String contents) throws CanNotReferenceException {
        for (Long id : ContentsParser.parseReference(contents)) {
            todoRepository.findByIdAndCompleted(id, false).orElseThrow(() -> new CanNotReferenceException("존재하지 않는 참조 id 입니다. : " + id));
        }
        return contents;
    }

    private String checkSelfReference(long id, String contents) throws CanNotReferenceException {
        for (Long inputId : ContentsParser.parseReference(contents)) {
            if (id == inputId) throw new CanNotReferenceException("자기 자신은 참조할 수 없습니다 : " + id);
        }
        return contents;
    }


}
