package org.springproject.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.library.entity.UserEntity;
import org.springproject.library.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional <UserEntity> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
