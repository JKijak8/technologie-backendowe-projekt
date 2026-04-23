package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ConflictException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ForbiddenException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.UserMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserRegisterRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserSummaryDTO fetchUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toUserSummaryDTO(user);
    }

    public Page<UserSummaryDTO> fetchUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toUserSummaryDTO);
    }

    public UserSummaryDTO createUser(UserSaveRequest request, List<RoleEnum> userRoles) {
        if (!request.isDataOnly()) {
            throw new BadRequestException("This endpoint doesn't accept user IDs.");
        }

        checkEmail(request.getEmail());
        checkRoles(request.getRoles(), userRoles);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return userMapper.toUserSummaryDTO(user);
    }

    public UserSummaryDTO createUser(UserRegisterRequest request) {
        checkEmail(request.getEmail());

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return userMapper.toUserSummaryDTO(user);
    }

    public UserSummaryDTO updateUser(UserSaveRequest request, List<RoleEnum> userRoles) {
        if (request.getId() == null) throw new BadRequestException("Id is required.");

        checkIfUserExists(request.getId());
        checkRoles(request.getRoles(), userRoles);

        User user = userRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("User not found."));
        if (!Objects.equals(request.getEmail(), user.getEmail())) checkEmail(request.getEmail());
        checkRoles(user.getRoles(), userRoles);

        userMapper.updateUser(request, user);
        userRepository.save(user);

        return userMapper.toUserSummaryDTO(user);
    }

    public void deleteUser(Long id, List<RoleEnum> userRoles) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
        checkRoles(user.getRoles(), userRoles);

        userRepository.delete(user);
    }

    protected void checkIfUserExists(Long id) {
        if (!userRepository.existsById(id)) throw new NotFoundException("User not found.");
    }

    protected void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new ConflictException("An account with this email already exists.");
    }

    protected void checkRoles(List<RoleEnum> requestRoles, List<RoleEnum> userRoles) {
        if (requestRoles.contains(RoleEnum.ADMIN)) {
            throw new ForbiddenException("You cannot create or modify an ADMIN using an API endpoint.");
        }
        if (requestRoles.contains(RoleEnum.MANAGER) && !userRoles.contains(RoleEnum.ADMIN)) {
            throw new ForbiddenException("Only an ADMIN can create or modify a MANAGER.");
        }
    }
}
