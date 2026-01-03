package com.todo.todoapp.controller;

import com.todo.todoapp.dto.TodoRequest;
import com.todo.todoapp.entity.Todo;
import com.todo.todoapp.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Todo create(@RequestBody TodoRequest todoRequest) {
        return todoService.create(todoRequest);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        return todoService.update(id, todoRequest);
    }

    @GetMapping("/{id}")
    public Todo getById(@PathVariable Long id) {
        return todoService.getById(id);
    }

    @GetMapping("/{id}/complete/{status}")
    public Todo complete(@PathVariable Long id,
                         @PathVariable boolean status) {
        return todoService.markComplete(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

    @GetMapping
    public List<Todo> getAll() {
        return todoService.findAll();
    }
}
