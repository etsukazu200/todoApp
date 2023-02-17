package app.todo_service.repositories;

import app.todo_service.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem,Long> {

  List<TodoItem> findByCompletedIsTrue();

    List<TodoItem> findByCompletedIsFalse();
}
