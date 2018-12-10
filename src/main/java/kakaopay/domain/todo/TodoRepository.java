package kakaopay.domain.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findAllByCompleted(boolean bool, Pageable p);

    Optional<Todo> findByIdAndCompleted(Long id, boolean completed);
}
