package kakaopay.service;

import kakaopay.domain.todo.Todo;
import kakaopay.domain.todo.TodoRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.util.ContentsParser;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class TodoService {
    private static final Logger logger = getLogger(TodoService.class);

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ReferenceService referenceService;

    public Page<Todo> findAllByCompleted(boolean bool, Pageable pageable) {
        return todoRepository.findAllByCompleted(bool, pageable);
    }

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Todo create(String contents) {
        Todo theTodo = todoRepository.save(new Todo(contents));
        this.createReference(theTodo);
        logger.debug("createTodo : {}", theTodo);
        return theTodo;
    }

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Todo delete(long id) {
        Todo theTodo = this.findByIdAndCompleted(id);
        theTodo.complete();
        referenceService.deleteWithTodo(theTodo);
        logger.debug("completeTodo : {}", theTodo);
        return todoRepository.save(theTodo);
    }

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Todo update(long id, String contents) {
        Todo theTodo = this.findByIdAndCompleted(id);
        this.updateReference(theTodo, ContentsParser.parseReference(contents));
        theTodo.update(contents);
        logger.debug("updateTodo : {}", theTodo);
        return todoRepository.save(theTodo);
    }

    private Todo findByIdAndCompleted(long id) {
        return todoRepository.findByIdAndCompleted(id, false).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다. : " + id));
    }

    private void createReference(Todo sourceTodo) {
        for (Long target : sourceTodo.makeReferences()) {
            referenceService.create(sourceTodo, this.findByIdAndCompleted(target));
        }
    }

    private void updateReference(Todo theTodo, List<Long> newTargets) {
        List<Long> curTargets = theTodo.makeReferences();

        List<Long> delTargets = new ArrayList<>(curTargets);
        delTargets.removeAll(newTargets);
        this.deleteReferences(theTodo, delTargets);

        List<Long> addTargets = new ArrayList<>(newTargets);
        addTargets.removeAll(curTargets);
        this.addReferences(theTodo, addTargets);
    }

    private void deleteReferences(Todo theTodo, List<Long> delTargets) {
        for (Long delTarget : delTargets) {
            referenceService.delete(theTodo, this.findByIdAndCompleted(delTarget));
        }
    }

    private void addReferences(Todo theTodo, List<Long> addTargets) {
        for (Long addTarget : addTargets) {
            referenceService.add(theTodo, this.findByIdAndCompleted(addTarget));
        }
    }


}
