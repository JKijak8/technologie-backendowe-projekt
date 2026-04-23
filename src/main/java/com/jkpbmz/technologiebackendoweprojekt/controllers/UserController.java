package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.JwtService;
import com.jkpbmz.technologiebackendoweprojekt.services.UserService;
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
@RequestMapping("/user")
public class UserController {
    UserService userService;
    JwtService jwtService;

    @GetMapping("")
    public UserSummaryDTO getUser(@RequestParam("userId") Long id){
        return userService.fetchUser(id);
    }

    @GetMapping("/list")
    public Page<UserSummaryDTO> findUsers(Pageable pageable) {
        return userService.fetchUsers(pageable);
    }

    @PostMapping("")
    public ResponseEntity<UserSummaryDTO> createUser(@RequestHeader(value = "Authorization") String authorization,
                                                     @RequestBody UserSaveRequest request,
                                                     UriComponentsBuilder builder) {
        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        UserSummaryDTO user = userService.createUser(request, userRoles);
        URI uri = builder.path("/user?userId={id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("")
    public UserSummaryDTO updateUser(@RequestHeader(value = "Authorization") String authorization,
            @RequestBody UserSaveRequest request) {
        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        return userService.updateUser(request, userRoles);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(@RequestHeader(value = "Authorization") String authorization,
            @RequestParam("userId") Long id) {
        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        userService.deleteUser(id, userRoles);
        return ResponseEntity.noContent().build();
    }
}
