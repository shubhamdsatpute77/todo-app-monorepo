package com.todo.todoapp.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
        @NotBlank String title,
        String description
) {}
