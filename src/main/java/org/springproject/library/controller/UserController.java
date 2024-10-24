package org.springproject.library.controller;

import jakarta.annotation.PostConstruct;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/library/user/")
public class UserController {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
    }

    @PostConstruct
    public void init(){
        UserEntity userAdmin = new UserEntity();
        userAdmin.setName("Kenneth");
        userAdmin.setLastname("Velasquez");
        userAdmin.setUsername("kennethAdmin");
        userAdmin.setEmail("kenneth@gmail.com");
        userAdmin.setBirthdate(new Date(2005,10,5));
        userAdmin.setPasswordEncrypted(passwordEncoder.encode("4dM1n"));
        userAdmin.addRole(Role.ADMIN);
        userAdmin.setStatusUser(true);
        UserEntity user = new UserEntity();
        user.setUsername("Bryan");
        user.setUsername("bryanUser");
        user.setName("Bryan");
        user.setLastname("Rodriguez");
        user.setEmail("bryan@gmail.com");
        user.setBirthdate(new Date(2005,10,15));
        user.setPasswordEncrypted(passwordEncoder.encode("Us3R"));
        user.addRole(Role.USER);
        user.setStatusUser(true);
        userService.addUser(userAdmin);
        userService.addUser(user);
    }

    @PostMapping("/")
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

    @GetMapping("/")
    public ResponseEntity getAllUser(){
        List <UserDto> listUser = userService.getAllUsers().stream()
                .map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok(listUser);
    }
}
