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

    @GetMapping("")
    public UserSummaryDTO getUser(@RequestParam("userId") Long id){
        return userService.fetchUser(id);
    }

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

    @PutMapping("")
    public UserSummaryDTO updateUser(@RequestBody UserSaveRequest request) {
        return userService.updateUser(request);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
