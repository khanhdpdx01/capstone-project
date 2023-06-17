package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.user.CreateUserDTO;
import io.github.khanhdpdx01.backend.dto.user.UpdateUser;
import io.github.khanhdpdx01.backend.dto.user.UserDTO;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
        UserDTO userDTO = userService.updateUser(updateUser);
        return ResponseEntity.status(200).body(userDTO);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        User registered = userService.createUser(createUserDTO);

        return ResponseEntity.status(200).body(registered);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);

        return ResponseEntity.status(200).body(user);
    }
}
