package app.todo_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_list")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @OneToMany(mappedBy = "todoList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    //private List<TodoItem> todoItems;


}
