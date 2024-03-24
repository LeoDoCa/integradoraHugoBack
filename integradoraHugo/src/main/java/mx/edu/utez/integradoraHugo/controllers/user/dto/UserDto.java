package mx.edu.utez.integradoraHugo.controllers.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.integradoraHugo.models.role.Role;
import mx.edu.utez.integradoraHugo.models.user.User;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;
    public User toEntity() {
        return new User(id, username, password, roles);
    }

}