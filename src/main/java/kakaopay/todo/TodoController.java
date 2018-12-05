package kakaopay.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public String showTodos(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }

    @PostMapping
    public String createTodo(Todo theTodo){
        todoRepository.save(theTodo);
        return "redirect:/";
    }
}
