package org.springproject.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springproject.library.entity.UserEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String lastname;
    private String username;
    private Date birthdate;
    private String email;
    private String password;

    public UserDto(UserEntity user) {
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.birthdate = user.getBirthdate();
        this.email = user.getEmail();
    }
}
