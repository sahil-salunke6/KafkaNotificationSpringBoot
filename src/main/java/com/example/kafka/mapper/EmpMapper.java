
package com.example.kafka.mapper;

import com.example.kafka.dto.EmpRequestDto;
import com.example.kafka.dto.EmpResponseDto;
import com.example.kafka.entity.Employee;

public class EmpMapper {

    public static Employee toEntity(EmpRequestDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        return employee;
    }

    public static EmpResponseDto toDto(Employee user) {
        EmpResponseDto dto = new EmpResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
