package org.springproject.library.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springproject.library.dto.UserDto;
import org.springproject.library.entity.Role;
import org.springproject.library.entity.UserEntity;
import org.springproject.library.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springproject.library.utils.Constants.ADMIN_ROLE;
import static org.springproject.library.utils.Constants.USER_ROLE;

@RestController
@RequestMapping("/api/v1/library/user/")
public class UserController {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserDto userDto){
        UserEntity userNew = new UserEntity();
        userNew.setName(userDto.getName());
        userNew.setLastname(userDto.getLastname());
        userNew.setUsername(userDto.getUsername());
        userNew.setEmail(userDto.getEmail());
        userNew.setBirthdate(userDto.getBirthdate());
        userNew.setPasswordEncrypted(passwordEncoder.encode(userDto.getPassword()));
        userNew.getRole().add(Role.USER);
        userNew.setStatusUser(true);
        UserEntity createUser = userService.addUser(userNew);
        UserDto createUserDto = new UserDto(createUser);
        return ResponseEntity.ok(createUserDto);
    }

    @RolesAllowed(ADMIN_ROLE)
    @GetMapping("/")
    public ResponseEntity getAllUser(){
        List <UserDto> listUser = userService.getAllUsers().stream()
                .map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok(listUser);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity getUserById(@PathVariable String id){
        Optional<UserDto> userSearch = userService.getUserById(id).map(user->new UserDto(user));
        if (userSearch.isPresent()) {
            return ResponseEntity.ok(userSearch.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RolesAllowed({USER_ROLE, ADMIN_ROLE})
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody UserDto userDto){
        Optional<UserEntity> userSearch = userService.getUserById(id);
        if ( userSearch.isPresent()) {
            userSearch.get().setName(userDto.getName());
            userSearch.get().setLastname(userDto.getLastname());
            userSearch.get().setUsername(userDto.getUsername());
            userSearch.get().setEmail(userDto.getEmail());
            userSearch.get().setBirthdate(userDto.getBirthdate());
            if(userDto.getPassword()!=null)
                userSearch.get().setPasswordEncrypted(passwordEncoder.encode(userDto.getPassword()));
            else userSearch.get().setPasswordEncrypted(userSearch.get().getPasswordEncrypted());
            return ResponseEntity.ok(userService.addUser(userSearch.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        Optional<UserEntity> userSearch = userService.getUserById(id);
        if ( userSearch.isPresent()) {
            userSearch.get().setStatusUser(false);
            return ResponseEntity.ok(userService.updateUser(userSearch.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
