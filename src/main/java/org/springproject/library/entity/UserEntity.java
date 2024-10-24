package org.springproject.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="users")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String lastname;
    private String username;
    private Date birthdate;
    private String email;
    private String passwordEncrypted;
    private List<Role> role = new ArrayList<>();
    private boolean statusUser;

    public void addRole(Role role) {
        if (!this.role.contains(role)) {
            this.role.add(role);
        }
    }
}
