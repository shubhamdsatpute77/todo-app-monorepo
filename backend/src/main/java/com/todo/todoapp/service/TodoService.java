package com.todo.todoapp.service;

import com.todo.todoapp.dto.TodoRequest;
import com.todo.todoapp.entity.Todo;

import java.util.List;

public interface TodoService {
    Todo create(TodoRequest todoRequest);
    Todo update(Long id, TodoRequest todoRequest);
    void delete(Long id);
    List<Todo> findAll();
    Todo getById(Long id);
    Todo markComplete(Long id, Boolean complete);
}
