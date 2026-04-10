package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.mappers.UserMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    public Page<UserSummaryDTO> fetchUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toUserSummaryDTO);
    }
}
