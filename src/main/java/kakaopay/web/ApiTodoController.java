package kakaopay.web;

import kakaopay.domain.Todo;
import kakaopay.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import support.result.Result;

@RestController
@RequestMapping("/api/todos")
public class ApiTodoController {
    @Autowired
    TodoService todoService;

    @PutMapping("{id}")
    public Result<Todo> update(@PathVariable long id, String contents) {
        try {
            return Result.ok(todoService.update(id, contents));
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        }
    }


}
