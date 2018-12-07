package kakaopay.service;

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
        return todoRepository.save(new Todo(checkContents(contents)));
    }

    public Todo update(long id, String contents) {
        Todo theTodo = todoRepository.findById(id).orElseThrow(IllegalStateException::new);
        theTodo.update(checkContents(contents));
        return todoRepository.save(theTodo);
    }

    private String checkContents(String contents) throws IllegalArgumentException{
        for (Long id : ContentsParser.parseReference(contents)){
            logger.debug("Exception : can't reference");
            todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("참조 id를 확인해주세요. : " + id));
        }
        return contents;
    }


}
