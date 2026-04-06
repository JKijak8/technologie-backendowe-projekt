package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeCreateRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    public EmployeeDTO findEmployee(@RequestParam("employeeId") Long employeeId) {
        return employeeService.fetchEmployee(employeeId);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        EmployeeDTO employeeDTO = employeeService.createEmployee(employeeCreateRequest);
        URI uri = uriComponentsBuilder.path("/employee?employeeId={id}").buildAndExpand(employeeDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(employeeDTO);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteEmployee(@RequestParam("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
