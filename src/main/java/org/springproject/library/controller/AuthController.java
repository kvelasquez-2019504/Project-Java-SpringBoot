package org.springproject.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springproject.library.dto.LoginDto;
import org.springproject.library.dto.TokenDto;
import org.springproject.library.entity.UserEntity;
import org.springproject.library.exception.InvalidCredentialsException;
import org.springproject.library.security.JwtUtil;
import org.springproject.library.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/library/auth")
public class AuthController {

    private final UserService userService;

    private final JwtUtil jwtUtil;


    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws InvalidCredentialsException {
        Optional<UserEntity> getUser = userService.getByUsername(loginDto.getUsername());
        if(getUser.isPresent()) {
            if(BCrypt.checkpw(loginDto.getPassword(),getUser.get().getPasswordEncrypted())){
                TokenDto tokenDto = jwtUtil.generateToken(getUser.get().getEmail()
                        ,getUser.get().getRole());
                return ResponseEntity.ok(tokenDto);
            }else{
                throw new InvalidCredentialsException();
            }
        }else{
            throw new InvalidCredentialsException();
        }
    }
}
