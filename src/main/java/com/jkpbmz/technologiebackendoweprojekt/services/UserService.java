package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ConflictException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.UserMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    public Page<UserSummaryDTO> fetchUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toUserSummaryDTO);
    }

    public UserSummaryDTO createUser(UserSaveRequest request) {
        if (!request.isDataOnly()) {
            throw new BadRequestException("This endpoint doesn't accept user IDs.");
        }

        checkEmail(request.getEmail());

        User user = userMapper.toUser(request);
        user = userRepository.save(user);

        return userMapper.toUserSummaryDTO(user);
    }

    public UserSummaryDTO updateUser(UserSaveRequest request) {
        if (request.getId() == null) throw new BadRequestException("Id is required.");

        checkIfUserExists(request.getId());

        User user = userRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("User not found."));
        if (!Objects.equals(request.getEmail(), user.getEmail())) checkEmail(request.getEmail());

        userMapper.updateUser(request, user);
        userRepository.save(user);

        return userMapper.toUserSummaryDTO(user);
    }

    public void deleteUser(Long id) {
        checkIfUserExists(id);
        userRepository.deleteById(id);
    }

    protected void checkIfUserExists(Long id) {
        if (!userRepository.existsById(id)) throw new NotFoundException("User not found.");
    }

    protected void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new ConflictException("An account with this email already exists.");
    }
}
