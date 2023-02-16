package app.todo_service.Controller;

import app.todo_service.entities.TodoItem;
import app.todo_service.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoItemService todoService;



    @PostMapping("/add-Todo")
    public TodoItem createTodo(@RequestParam String title) {
        return todoService.createTodo(title);
    }

    @PutMapping("/{id}/complete")
    public void markTodoCompleted(@PathVariable Long id) {
        todoService.markTodoCompleted(id);
    }

    @PutMapping("/{id}/uncomplete")
    public void markTodoUncompleted(@PathVariable Long id) {
        todoService.markTodoUncompleted(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @GetMapping("all")
    public List<TodoItem> listTodos(){
        return  todoService.listTodos();
    }
}