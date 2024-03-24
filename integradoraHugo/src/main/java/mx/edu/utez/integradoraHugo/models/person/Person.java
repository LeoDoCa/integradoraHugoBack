package mx.edu.utez.integradoraHugo.models.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.integradoraHugo.models.user.User;
import java.time.LocalDate;

@Entity
@Table(name = "people")
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(length = 50)
    private String lastname;
    @Column(columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status; //Wrappers boolean
    @OneToOne(mappedBy = "person", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"person"})
    private User user;
    public Person(String name, String surname, String lastname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public Person(Long id, String name, String surname, String lastname, LocalDate birthDate, Boolean status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.status = status;
    }

    public Person(Long id, String name, String surname, String lastname, LocalDate birthDate, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Person(Long id, String name, String surname, String lastname, LocalDate birthDate, Boolean status, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.status = status;
        this.user = user;
    }
}
