package com.todo.todoapp.service;

import com.todo.todoapp.dto.TodoRequest;
import com.todo.todoapp.entity.Todo;
import com.todo.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo create(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Long id, TodoRequest todoRequest) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    @Override
    public Todo markComplete(Long id, Boolean complete) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(complete);
        return todoRepository.save(todo);
    }
}
