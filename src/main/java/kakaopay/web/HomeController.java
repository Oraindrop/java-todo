package kakaopay.web;

import kakaopay.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String start(){
        return "forward:/todos";
    }
}
