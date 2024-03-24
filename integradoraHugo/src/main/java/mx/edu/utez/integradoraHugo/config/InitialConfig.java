package mx.edu.utez.integradoraHugo.config;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.integradoraHugo.models.person.Person;
import mx.edu.utez.integradoraHugo.models.person.PersonRepository;
import mx.edu.utez.integradoraHugo.models.role.Role;
import mx.edu.utez.integradoraHugo.models.role.RoleRepository;
import mx.edu.utez.integradoraHugo.models.user.User;
import mx.edu.utez.integradoraHugo.models.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run(String... args) throws Exception {
        Role adminRole = getOrSaveRole(new Role("ADMIN_ROLE"));
        getOrSaveRole(new Role("USER_ROLE"));
        //Crear un usuario para que puedan iniciar sesión (person, user, user_role)
        Person person = savePerson(
                new Person("Leonardo", "Dorantes", "Castañeda",
                        LocalDate.of(1998, 1, 19))
        );
        User user = getOrSaveUser(
                new User("admin", encoder.encode("admin"), person)
        );
        saveUserRoles(user.getId(), adminRole.getId());
    }
    @Transactional
    public Role getOrSaveRole(Role role) {
        Optional<Role> foundRole = roleRepository.findByName(role.getName());
        return foundRole.orElseGet(() -> roleRepository.saveAndFlush(role));
    }
    @Transactional
    public Person savePerson(Person person) {
        return personRepository.saveAndFlush(person);
    }
    @Transactional
    public User getOrSaveUser(User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser.orElseGet(() -> userRepository.saveAndFlush(user));
    }
    @Transactional
    public void saveUserRoles(Long id, Long roleId) {
        Long userRoleId = userRepository.getIdUserRoles(id, roleId);
        if (userRoleId == null)
            userRepository.saveUserRole(id, roleId);
    }

}
