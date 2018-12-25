package kakaopay.web;

import kakaopay.domain.todo.Todo;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.http.*;
import support.test.BaseTest;

import static org.slf4j.LoggerFactory.getLogger;


public class ApiTodoAcceptanceTest extends BaseTest {
    private static final Logger logger = getLogger(ApiTodoAcceptanceTest.class);

    @Test
    public void create() {
        String contents = "newContents";
        ResponseEntity<Todo> response = createResource(contents);
        softly.assertThat(response.getBody().getContents()).isEqualTo(contents);
    }

    @Test
    public void create_with_invalid_contents() throws Exception {
        String contents = "newContents @555";
        ResponseEntity<Void> response = template().postForEntity("/api/todos", contents, Void.class);
        logger.debug("response : {}", response);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Todo> createResource(String contents) {
        ResponseEntity<Todo> response = template().postForEntity("/api/todos", contents, Todo.class);
        logger.debug("response : {}", response);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        return response;
    }

    @Test
    public void update() {
        ResponseEntity<Todo> response = createResource("newContents");
        String location = response.getHeaders().getLocation().getPath();

        String updateContents = "updateTest";
        ResponseEntity<Todo> responseEntity =
                template().exchange(location, HttpMethod.PUT, createHttpEntity(updateContents), Todo.class);

        logger.debug("responseEntity : {}", responseEntity);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(responseEntity.getBody().getContents()).isEqualTo(updateContents);
    }

    @Test
    public void update_with_invalid_contents() {
        ResponseEntity<Todo> response = createResource("newContents @1");
        long curId = response.getBody().getId();

        String updateContents = "updateTest @" + curId;

        String location = response.getHeaders().getLocation().getPath();
        location = location.substring(0, location.length()-1).concat("1");

        ResponseEntity<Void> responseEntity =
                template().exchange(location, HttpMethod.PUT, createHttpEntity(updateContents), Void.class);

        logger.debug("responseEntity : {}", responseEntity);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private HttpEntity createHttpEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(body, headers);
    }

    @Test
    public void delete() {
        ResponseEntity<Todo> response = createResource("newContents");
        String location = response.getHeaders().getLocation().getPath();

        ResponseEntity<Todo> responseEntity =
                template().exchange(location, HttpMethod.DELETE, HttpEntity.EMPTY, Todo.class);

        logger.debug("responseEntity : {}", responseEntity);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete_with_invalid_contents() {
        ResponseEntity<Todo> response = createResource("newContents @1");
        String location = response.getHeaders().getLocation().getPath();
        location = location.substring(0, location.length()-1).concat("1");

        ResponseEntity<Void> responseEntity =
                template().exchange(location, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        logger.debug("responseEntity : {}", responseEntity);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
