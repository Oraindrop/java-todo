package kakaopay.web;

import kakaopay.domain.todo.Todo;
import kakaopay.service.TodoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import support.util.PagingUtil;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class HomeController {
    private static final Logger logger = getLogger(HomeController.class);
    public static final int SHOW_SIZE_IN_ONE_PAGE = 5;

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String home(Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = SHOW_SIZE_IN_ONE_PAGE) Pageable pageable) {
        Page<Todo> todos = todoService.findAllByCompleted(false, pageable);
        logger.debug("pages Total : {}", todos.getTotalPages());
        logger.debug("curPage : {}", pageable.getPageNumber());
        PagingUtil pageUtil = new PagingUtil(pageable.getPageNumber(), todos.getTotalPages() - 1);
        model.addAttribute("pageUtil", pageUtil);
        model.addAttribute("todos", todos);
        return "index";
    }
}
