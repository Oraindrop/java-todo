package kakaopay.web;

import kakaopay.domain.todo.Todo;
import kakaopay.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import support.result.Result;

@RestController
@RequestMapping("/api/todos")
public class ApiTodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public Result<Todo> create(String contents) {
        return Result.ok(todoService.create(contents));
    }

    @PutMapping("/{id}")
    public Result<Todo> update(@PathVariable long id, String contents) {
        return Result.ok(todoService.update(id, contents));
    }

    @DeleteMapping("/{id}")
    public Result<Todo> delete(@PathVariable long id) {
        return Result.ok(todoService.delete(id));
    }
}
