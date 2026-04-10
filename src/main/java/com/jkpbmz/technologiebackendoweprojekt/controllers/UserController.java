package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @GetMapping("/list")
    public Page<UserSummaryDTO> findUsers(Pageable pageable) {
        return userService.fetchUsers(pageable);
    }

    @PostMapping("")
    public ResponseEntity<UserSummaryDTO> createUser(@RequestBody UserSaveRequest request,
                                                     UriComponentsBuilder builder) {
        UserSummaryDTO user = userService.createUser(request);
        URI uri = builder.path("/user?userId={id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }
}
