package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @GetMapping("/list")
    public Page<UserSummaryDTO> findUsers(Pageable pageable) {
        return userService.fetchUsers(pageable);
    }
}
