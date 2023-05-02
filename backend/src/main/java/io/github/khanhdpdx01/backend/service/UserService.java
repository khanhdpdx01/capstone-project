package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.request.JwtRequest;
import io.github.khanhdpdx01.backend.dto.response.JwtResponse;
import io.github.khanhdpdx01.backend.dto.user.CreateUserDTO;
import io.github.khanhdpdx01.backend.dto.user.UpdateUser;
import io.github.khanhdpdx01.backend.dto.user.UserDTO;
import io.github.khanhdpdx01.backend.dto.user.UserPrinciple;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.repository.UserRepository;
import io.github.khanhdpdx01.backend.util.JwtTokenUtil;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.github.khanhdpdx01.backend.common.AppConstant.ADMIN;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername())
                .setPassword(passwordEncoder.encode(createUserDTO.getPassword()))
                .setRole(ADMIN)
                .setFullName(createUserDTO.getFullName())
                .setActive(true);

        User newUser = userRepository.save(user);
        return newUser;
    }

    public Page<UserDTO> getAllUsers(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<User> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            pageRoom = userRepository.findAll(pageable);
        } else {
            pageRoom = userRepository.search(keyword, pageable);
        }

        List<UserDTO> userDTOs = mapList(pageRoom.getContent());

        return new PageImpl<>(userDTOs, pageable, pageRoom.getTotalElements());
    }

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId())
                .setUsername(user.getUsername())
                .setFullName(user.getFullName())
                .setActive(user.isActive())
                .setRole(user.getRole());

        return userDTO;
    }

    public List<UserDTO> mapList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach(user -> userDTOs.add(map(user)));
        return userDTOs;
    }

    public UserDTO updateUser(UpdateUser updateUser) {
        User user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new RuntimeException("User is not found"));

        user.setActive(updateUser.isActive());
        User updatedUser = userRepository.save(user);
        return map(updatedUser);
    }

    public JwtResponse login(JwtRequest authenticationRequest) throws ApiRequestException {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ApiRequestException("Credentials are not valid!");
        }

        // Insert username and password into context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create token
        UserPrinciple user = (UserPrinciple) authentication.getPrincipal();
        String accessToken = jwtTokenUtil.generateToken(user);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setRole(user.getRole());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(accessToken);

        return jwtResponse;
    }
}
