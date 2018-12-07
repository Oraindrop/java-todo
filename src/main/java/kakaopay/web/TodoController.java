package kakaopay.web;

import kakaopay.domain.Todo;
import kakaopay.domain.TodoRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private static final Logger logger = getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping
    public String create(Todo theTodo){
        todoRepository.save(theTodo);
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String modify(@PathVariable long id){
        logger.info(String.valueOf(todoRepository.findById(id).orElse(null)));
        todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return null;
    }
}
