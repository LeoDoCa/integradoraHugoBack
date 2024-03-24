package mx.edu.utez.integradoraHugo.controllers.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.integradoraHugo.controllers.user.dto.UserDto;
import mx.edu.utez.integradoraHugo.models.person.Person;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class PersonDto {
    private Long id;
    private String name;
    private String surname;
    private String lastname;
    private LocalDate birthDate;
    private Boolean status;

    private UserDto user;

    public Person toEntity() {
        if (user == null)
            return new Person(id, name, surname, lastname, birthDate, status);
        return new Person(id, name, surname, lastname, birthDate, status, user.toEntity());
    }
}
