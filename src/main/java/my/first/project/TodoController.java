package my.first.project;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(path = "/api/todo")
public class TodoController {

    private AtomicInteger increment = new AtomicInteger();

    private Map<Integer, Todo> todos = new ConcurrentHashMap<>();

    @RequestMapping
    public Collection<Todo> list() {
        return todos.values();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo add(@RequestParam String text) {
        var todo = new Todo(increment.getAndIncrement(), text);
        todos.put(todo.getId(), todo);
        return todo;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Integer id) {
        todos.remove(id);
    }

}
