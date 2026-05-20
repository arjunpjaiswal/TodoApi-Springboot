package org.example.todoapistring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService{
    public String doSomething(){
        return "Something";
    }
}
