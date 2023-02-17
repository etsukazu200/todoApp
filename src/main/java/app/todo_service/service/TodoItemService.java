package app.todo_service.service;


import app.todo_service.entities.TodoItem;
import app.todo_service.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TodoItemService {

     @Autowired
     TodoRepo todoRepo;



    public TodoItem createTodo(String title) {
        TodoItem todo = new TodoItem();
        todo.setTitle(title);
        todo.setCompleted(false);
        return todoRepo.save(todo);
    }

    public void  markTodoCompleted(Long id) {
        TodoItem  todo = todoRepo.findById(id).orElseThrow( () -> new EntityNotFoundException("Aucune todoItem  n'est trouvée portant l'ID : "+id));
        if (todo != null) {
            todo.setCompleted(true);
             todoRepo.save(todo);
        }

    }

    public void  markTodoUncompleted(Long id) {
        TodoItem  todo = todoRepo.findById(id).orElseThrow( () -> new EntityNotFoundException("Aucune todoItem  n'est trouvée portant l'ID : "+id));;
        if (todo != null) {
            todo.setCompleted(false);
             todoRepo.save(todo);
        }

    }

    public void deleteTodo(Long id) {
        todoRepo.deleteById(id);
    }

    public List<TodoItem> listTodos() {
        return todoRepo.findAll();
    }

    public List<TodoItem> listTodosfiltred(String filterBy) {
          if(filterBy.equals("all")){
              return listTodos();
          }
          else if(filterBy.equals("completed")) {
             return  todoRepo.findByCompletedIsTrue();
        } else {
         return   todoRepo.findByCompletedIsFalse();
    }
}
}








