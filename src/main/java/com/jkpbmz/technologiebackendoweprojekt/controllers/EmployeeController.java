package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.EmployeeService;
import com.jkpbmz.technologiebackendoweprojekt.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final int MAX_PAGE_SIZE = 25;

    private final EmployeeService employeeService;
    private final JwtService jwtService;

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
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestHeader(value = "Authorization") String authorization,
                                                      @RequestBody EmployeeSaveRequest employeeSaveRequest,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        EmployeeDTO employeeDTO = employeeService.createEmployee(employeeSaveRequest, userRoles);
        URI uri = uriComponentsBuilder.path("/employee?employeeId={id}").buildAndExpand(employeeDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(employeeDTO);
    }

    @PutMapping("")
    public EmployeeDTO updateEmployee(@RequestHeader(value = "Authorization") String authorization,
                                      @RequestParam("employeeId") Long employeeId,
                                      @RequestBody EmployeeSaveRequest employeeSaveRequest) {
        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        return employeeService.updateEmployee(employeeId, employeeSaveRequest, userRoles);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteEmployee(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestParam("employeeId") Long employeeId) {
        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        employeeService.deleteEmployee(employeeId, userRoles);
        return ResponseEntity.noContent().build();
    }
}
