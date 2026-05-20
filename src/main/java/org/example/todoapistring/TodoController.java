package org.example.todoapistring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private TodoService todoService;
    private TodoService todoService2;
private static List<Todo>todoList;

public TodoController(
        @Qualifier("anotherTodoService") TodoService todoService,
        @Qualifier("fakeTodoService")TodoService todoService2){
    this.todoService = todoService;
    this.todoService2=todoService2;
    todoList=new ArrayList<>();
    todoList.add(new Todo(1,false,"Todo 1",1));
    todoList.add(new Todo(2,true,"Todo 2",2));
}
@GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted){
    System.out.println("Incoming Query params"+isCompleted);
    return ResponseEntity.ok(todoList);
}
@PostMapping
public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
    /*
    * we can use this annotation
    *  to  set the status code @ResponseStatus(HttpStatus.CREATED)
    *
    * */
    todoList.add(newTodo);
   return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
}
    @GetMapping("/{todoId}")
    public ResponseEntity<?>getTodoById(@PathVariable int todoId ){
            for(Todo todo:todoList){
                if(todo.getId()==todoId){
                    return ResponseEntity.ok(todo);
                }
            }

            return  ResponseEntity.
                    status(HttpStatus.NOT_FOUND).body(Map.of("message","Todo Not Found"));
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?>deleteTodoById(@PathVariable int todoId){
    boolean removed=todoList.removeIf(todo->todo.getId()==todoId);
    if(removed){
        return ResponseEntity.ok(
                Map.of("message","Todo deleted succesfully")
        );
    }
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("message","Todo not found"));
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<?>updateTodo(@PathVariable int todoId,@RequestBody Todo updatedTodo){
    for(int i=0;i<todoList.size();i++){
        Todo existingTodo=todoList.get(i);
        if(existingTodo.getId()==todoId){
            updatedTodo.setId(todoId);
            todoList.set(i,updatedTodo);
            return ResponseEntity.ok(updatedTodo);
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Todo Not Found"));
    }
    @PatchMapping("/{todoId}")
    public ResponseEntity<?>patchTodo(
            @PathVariable int todoId,
            @RequestBody Map<String, Object>updates
    ){
    for(Todo todo:todoList){
        if(todo.getId()==todoId){
            if(updates.containsKey("title")){
                todo.setTitle((String)updates.get("title"));
            }
        }
        if(updates.containsKey("completed")){
            todo.setCompleted((Boolean)updates.get("completed"));
        }
        if(updates.containsKey("userId")){
            todo.setUserId((Integer)updates.get("userId"));
        }
         return ResponseEntity.ok(todo);
    }
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("message","Todo Not Found"));
    }
}
