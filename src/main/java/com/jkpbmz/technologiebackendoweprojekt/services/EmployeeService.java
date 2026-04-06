package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Employee;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.EmployeeMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeCreateRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.EmployeeRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeDTO fetchEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new NotFoundException("Employee not found.");
        }
        return employeeMapper.toEmployeeDTO(employee);
    }

    public Page<EmployeeSummaryDTO> fetchEmployeeList(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employeeMapper::toEmployeeSummaryDTO);
    }

    public EmployeeDTO createEmployee(EmployeeCreateRequest request) {
        Employee employee = employeeMapper.toEmployee(request, positionRepository);
        employeeRepository.save(employee);

        return employeeMapper.toEmployeeDTO(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new NotFoundException("Employee not found.");
        }
        employeeRepository.delete(employee);
    }
}
