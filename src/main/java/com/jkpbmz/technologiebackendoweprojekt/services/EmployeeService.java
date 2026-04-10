package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Employee;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ConflictException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.EmployeeMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.EmployeeRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;
    private final UserRepository userRepository;

    private final UserService userService;

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

    public EmployeeDTO createEmployee(EmployeeSaveRequest request) {
        if (request.getUser() != null) {
            if (request.getUser().isIdOnly()) {
                userService.checkIfUserExists(request.getUser().getId());
                checkIfEmployeeAssigned(request.getUser().getId());
            }
            else userService.checkEmail(request.getUser().getEmail());
        }
        Employee employee = employeeMapper.toEmployee(request, positionService, userRepository);
        employeeRepository.save(employee);

        return employeeMapper.toEmployeeDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeSaveRequest request) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new NotFoundException("Employee not found.");
        }
        if (request.getUser() != null) {
            if (!request.getUser().isIdOnly() &&
                    !request.getUser().isDataOnly() &&
                    !request.getUser().getId().equals(employee.getUser().getId())) {
                throw new BadRequestException("Id for User data update does not match the user's Id.");
            }
            if (request.getUser().isIdOnly()) {
                userService.checkIfUserExists(request.getUser().getId());
                checkIfEmployeeAssigned(request.getUser().getId());
            }
            else if (!Objects.equals(
                    request.getUser().getEmail(),
                    employee.getUser().getEmail()
            ) || request.getUser().isDataOnly()) userService.checkEmail(request.getUser().getEmail());
        }

        employeeMapper.updateEmployee(request, employee, positionService, userRepository);
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

    private void checkIfEmployeeAssigned(Long id) {
        if (employeeRepository.existsByUser_Id(id)) {
            throw new ConflictException("This account already has an assigned employee.");
        }
    }
}
