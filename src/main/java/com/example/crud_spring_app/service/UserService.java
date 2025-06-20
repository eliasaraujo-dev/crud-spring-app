package com.example.crud_spring_app.service;

import com.example.crud_spring_app.dto.PageResponseDTO;
import com.example.crud_spring_app.dto.UserRequestDTO;
import com.example.crud_spring_app.dto.UserResponseDTO;
import com.example.crud_spring_app.exception.EmailAlreadyExistsException;
import com.example.crud_spring_app.exception.UserNotFoundException;
import com.example.crud_spring_app.model.User;
import com.example.crud_spring_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public UserResponseDTO create(UserRequestDTO dto) {
        validateEmail(dto.email());
        User user = new User();
        user.setEmail(dto.email());
        user.setName(dto.name());
        User savedUser = repository.save(user);
        return toDTO(savedUser);
    }

    public List<UserResponseDTO> list() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public PageResponseDTO<UserResponseDTO> listPaginated(Pageable pageable) {
        Page<User> userPage = repository.findAll(pageable);
        List<UserResponseDTO> content = userPage.getContent().stream()
                .map(this::toDTO)
                .toList();
        
        return new PageResponseDTO<>(
                content,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.hasNext(),
                userPage.hasPrevious()
        );
    }

    public List<UserResponseDTO> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return toDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        
        // Verificar se o email mudou e se já existe
        if (!user.getEmail().equals(dto.email())) {
            validateEmail(dto.email());
        }
        
        user.setEmail(dto.email());
        user.setName(dto.name());
        User updatedUser = repository.save(user);
        return toDTO(updatedUser);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private void validateEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}