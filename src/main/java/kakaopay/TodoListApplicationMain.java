package kakaopay;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class TodoListApplicationMain {
    private static final Logger logger = getLogger(TodoListApplicationMain.class);

    public static void main(String[] args) {
        logger.info("hello logger");
        SpringApplication.run(TodoListApplicationMain.class);
    }
}
