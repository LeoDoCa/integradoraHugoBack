package mx.edu.utez.integradoraHugo.models.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.integradoraHugo.models.role.Role;
import mx.edu.utez.integradoraHugo.models.person.Person;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false, unique = true)
    private String username;
    @Column(length = 150, nullable = false)
    private String password;
    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean blocked;
    private String token;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.MERGE)
    private Set<Role> roles;
    @OneToOne
    @JoinColumn(name = "person_id", unique = true)
    private Person person;

    public User(Long id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = true;
        this.blocked = true;
        this.createdAt = LocalDateTime.now();
    }

    public User(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.status = true;
        this.createdAt = LocalDateTime.now();
        this.blocked = true;
    }

    public User(Long id, String username, String password, Boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }
}