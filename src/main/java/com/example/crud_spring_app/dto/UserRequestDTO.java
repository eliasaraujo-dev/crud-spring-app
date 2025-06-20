package com.example.crud_spring_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    String email,
    
    @NotBlank(message = "O nome é obrigatório")
    String name
) {}