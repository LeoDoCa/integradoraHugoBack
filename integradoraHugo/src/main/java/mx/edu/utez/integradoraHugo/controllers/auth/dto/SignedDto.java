package mx.edu.utez.integradoraHugo.controllers.auth.dto;


import lombok.Value;
import mx.edu.utez.integradoraHugo.models.role.Role;
import mx.edu.utez.integradoraHugo.models.user.User;

import java.util.List;

@Value
public class SignedDto {
    String token;
    String tokenType;
    User user;
    List<Role> roles;
}

