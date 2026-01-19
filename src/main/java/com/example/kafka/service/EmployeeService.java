package com.example.kafka.service;

import com.example.kafka.dto.EmpRequestDto;
import com.example.kafka.dto.EmpResponseDto;
import com.example.kafka.entity.Employee;
import com.example.kafka.mapper.EmpMapper;
import com.example.kafka.repository.EmployeeRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public EmpResponseDto create(EmpRequestDto dto) {
        Employee saved = repository.save(EmpMapper.toEntity(dto));
        return EmpMapper.toDto(saved);
    }

    public EmpResponseDto getById(Long id) {
        Employee Employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
        return EmpMapper.toDto(Employee);
    }

    public List<EmpResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(EmpMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmpResponseDto update(Long id, EmpRequestDto dto) {
        Employee Employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
        Employee.setName(dto.getName());
        Employee.setEmail(dto.getEmail());
        return EmpMapper.toDto(repository.save(Employee));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id)));
    }
    
}
