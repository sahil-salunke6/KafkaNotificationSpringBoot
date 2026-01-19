package com.example.kafka.controller;

import com.example.kafka.dto.EmpRequestDto;
import com.example.kafka.dto.EmpResponseDto;
import com.example.kafka.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class EmpController {

    private final EmployeeService service;

    /**
     * CREATE USER
     * 201 - Created
     * 400 - Validation failure
     * 409 - Duplicate resource
     */
    @PostMapping
    public ResponseEntity<EmpResponseDto> create(
            @Valid @RequestBody EmpRequestDto dto) {

        EmpResponseDto response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET USER BY ID
     * 200 - Success
     * 404 - User not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpResponseDto> get(@PathVariable Long id) {

        EmpResponseDto response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * GET ALL USERS
     * 200 - Success
     * 204 - No content
     */
    @GetMapping
    public ResponseEntity<List<EmpResponseDto>> all() {

        List<EmpResponseDto> users = service.getAll();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }

    /**
     * UPDATE USER
     * 200 - Updated
     * 400 - Validation failure
     * 404 - User not found
     * 409 - Duplicate resource
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody EmpRequestDto dto) {

        EmpResponseDto response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE USER
     * 204 - Deleted
     * 404 - User not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
