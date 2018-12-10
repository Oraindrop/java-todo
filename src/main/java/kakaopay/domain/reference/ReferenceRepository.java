package kakaopay.domain.reference;

import kakaopay.domain.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    List<Reference> findAllBySource(Todo source);

    List<Reference> findAllByTarget(Todo target);

    Optional<Reference> findBySourceAndTarget(Todo source, Todo target);
}
