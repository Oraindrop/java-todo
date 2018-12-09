package kakaopay.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import kakaopay.CanNotReferenceException;
import kakaopay.domain.Todo;
import kakaopay.domain.TodoRepository;
import kakaopay.service.TodoService;
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
    private TodoService todoService;

    @PostMapping
    public String create(Model model, String contents) {
        try {
            todoService.create(contents);
            return "redirect:/";
        } catch (CanNotReferenceException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id, Model model){
        try {
            todoService.delete(id);
            return "redirect:/";
        } catch (IllegalStateException | CanNotReferenceException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";        }
    }

}
