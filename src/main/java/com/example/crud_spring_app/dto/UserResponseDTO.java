package com.example.crud_spring_app.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
    Long id, 
    String email, 
    String name,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}