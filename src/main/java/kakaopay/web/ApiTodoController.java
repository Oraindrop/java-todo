package kakaopay.web;

import kakaopay.domain.todo.Todo;
import kakaopay.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class ApiTodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo create(String contents) {
        return todoService.create(contents);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable long id, String contents) {
        return todoService.update(id, contents);
    }

    @DeleteMapping("/{id}")
    public Todo delete(@PathVariable long id) {
        return todoService.delete(id);
    }
}
