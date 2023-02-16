package app.todo_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean completed = false;

    //  private LocalDate targetDate = LocalDate.now();

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "todoList_id", nullable = false)
    // @JsonIgnore
    // private TodoList todoList;


}
