package org.example.todoapistring;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {
private static List<Todo>todoList;
public TodoController(){
    todoList=new ArrayList<>();
    todoList.add(new Todo(1,false,"Todo 1",1));
    todoList.add(new Todo(2,true,"Todo 2",1));
}
@GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){
    return ResponseEntity.status(HttpStatus.OK).body(todoList);
}

public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
    /*
    * we can use this annotation to set the status code @ResponseStatus(HttpStatus.CREATED)
    *
    * */
    todoList.add(newTodo);
   return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
}
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?>getTodoById(@PathVariable int todoId ){
            for(Todo todo:todoList){
                if(todo.getId()==todoId){
                    return ResponseEntity.ok(todo);
                }
            }

            return  ResponseEntity.
                    status(HttpStatus.NOT_FOUND).body(Map.of("message","Todo Not Found"));
    }
}