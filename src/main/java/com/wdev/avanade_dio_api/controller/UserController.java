package com.wdev.avanade_dio_api.controller;

import com.wdev.avanade_dio_api.docs.UserControllerDoc;
import com.wdev.avanade_dio_api.dto.UserDto;
import com.wdev.avanade_dio_api.model.User;
import com.wdev.avanade_dio_api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@Tag(name = "User")
public class UserController implements UserControllerDoc {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        var user = userService.FindById(id);
        return ResponseEntity.ok(UserDto.fromEntity(user));
    }

    @Override
    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        var userToCreate = userDto.toEntity();
        var userCreated = userService.create(userToCreate);
        UserDto responseDto = UserDto.fromEntity(userCreated);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id()).toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User userToUpdate = userDto.toEntity();
        User userUpdated = userService.update(id, userToUpdate);
        return ResponseEntity.ok(UserDto.fromEntity(userUpdated));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
