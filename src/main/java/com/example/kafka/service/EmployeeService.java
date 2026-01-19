package com.example.kafka.service;

import com.example.kafka.dto.EmpRequestDto;
import com.example.kafka.dto.EmpResponseDto;
import com.example.kafka.entity.Employee;
import com.example.kafka.exception.DuplicateResourceException;
import com.example.kafka.exception.ResourceNotFoundException;
import com.example.kafka.mapper.EmpMapper;
import com.example.kafka.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository repository;

    /**
     * CREATE
     */
    public EmpResponseDto create(EmpRequestDto dto) {

        // Optional: Check duplicate email
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException(
                    "Employee already exists with email: " + dto.getEmail()
            );
        }

        try {
            Employee saved = repository.save(EmpMapper.toEntity(dto));
            return EmpMapper.toDto(saved);
        } catch (DataIntegrityViolationException ex) {
            // DB-level unique constraint safety net
            throw new DuplicateResourceException("Duplicate employee data");
        }
    }

    /**
     * GET BY ID
     */
    @Transactional(readOnly = true)
    public EmpResponseDto getById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id)
                );

        return EmpMapper.toDto(employee);
    }

    /**
     * GET ALL
     */
    @Transactional(readOnly = true)
    public List<EmpResponseDto> getAll() {

        return repository.findAll()
                .stream()
                .map(EmpMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * UPDATE
     */
    public EmpResponseDto update(Long id, EmpRequestDto dto) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id)
                );

        // Optional duplicate check during update
        if (!employee.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already in use: " + dto.getEmail()
            );
        }

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());

        try {
            return EmpMapper.toDto(repository.save(employee));
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Duplicate employee data");
        }
    }

    /**
     * DELETE
     */
    public void delete(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id)
                );

        repository.delete(employee);
    }
}
