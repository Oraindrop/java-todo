package kakaopay.service;

import kakaopay.domain.reference.Reference;
import kakaopay.domain.reference.ReferenceRepository;
import kakaopay.domain.todo.Todo;
import kakaopay.validate.CycleDetector;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
public class ReferenceService {
    private static final Logger logger = getLogger(ReferenceService.class);

    @Autowired
    private ReferenceRepository referenceRepository;

    public void create(Todo source, Todo target) {
        logger.debug("create reference");
        referenceRepository.save(new Reference(source, target));
    }

    public void deleteWithTodo(Todo theTodo) {
        // theTodo 를 target 으로 하는 reference 가 없어야 삭제 가능
        if (referenceRepository.findAllByTarget(theTodo).size() != 0) {
            throw new IllegalArgumentException("해당 id를 참조하는 Todo가 존재하여 완료할 수 없습니다. : " + theTodo.getId());
        }
        this.deleteFromSourceTodo(theTodo);
    }

    public void delete(Todo source, Todo target) {
        logger.debug("delete reference {} -> {}", source, target);
        referenceRepository.delete(this.findBySourceAndTarget(source, target));
    }

    public void add(Todo source, Todo target) {
        referenceRepository.save(new Reference(source, target));
        CycleDetector.detectCycle(referenceRepository.findAll());
        logger.debug("add reference {} -> {}", source, target);
    }

    // theTodo 를 source 로 하는 reference 삭제
    private void deleteFromSourceTodo(Todo theTodo) {
        Iterable<Reference> iterator = new ArrayList(referenceRepository.findAllBySource(theTodo));
        for (Reference aReference : iterator) {
            referenceRepository.delete(aReference);
            logger.debug("delete reference : {}", aReference);
        }
    }

    private Reference findBySourceAndTarget(Todo source, Todo target) {
        return referenceRepository.findBySourceAndTarget(source, target).orElseThrow(IllegalArgumentException::new);
    }

}
