package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final int MAX_PAGE_SIZE = 25;

    private final EmployeeService employeeService;

    @GetMapping("")
    public EmployeeDTO findEmployee(@RequestParam("employeeId") Long employeeId) {
        return employeeService.fetchEmployee(employeeId);
    }

    @GetMapping("/list")
    public Page<EmployeeSummaryDTO> findEmployeeList(Pageable pageable) {
        if (pageable.getPageSize() > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size exceeds maximum. Maximum allowed is " + MAX_PAGE_SIZE);
        }

        return employeeService.fetchEmployeeList(pageable);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeSaveRequest employeeSaveRequest,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        EmployeeDTO employeeDTO = employeeService.createEmployee(employeeSaveRequest);
        URI uri = uriComponentsBuilder.path("/employee?employeeId={id}").buildAndExpand(employeeDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(employeeDTO);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteEmployee(@RequestParam("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
