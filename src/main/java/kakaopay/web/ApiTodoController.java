package kakaopay.web;

import kakaopay.domain.todo.Todo;
import kakaopay.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/todos")
public class ApiTodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody String contents) {
        HttpHeaders responseHeader = new HttpHeaders();
        Todo createTodo = todoService.create(contents);
        responseHeader.setLocation(URI.create("/api/todos/" + createTodo.getId()));
        return new ResponseEntity<Todo>(createTodo, responseHeader, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable long id, @RequestBody String contents) {
        return todoService.update(id, contents);
    }

    @DeleteMapping("/{id}")
    public Todo delete(@PathVariable long id) {
        return todoService.delete(id);
    }
}
