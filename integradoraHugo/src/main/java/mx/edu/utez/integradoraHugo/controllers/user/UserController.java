package mx.edu.utez.integradoraHugo.controllers.user;

import jakarta.validation.Valid;
import mx.edu.utez.integradoraHugo.config.ApiResponse;

import mx.edu.utez.integradoraHugo.controllers.person.dto.PersonDto;
import mx.edu.utez.integradoraHugo.controllers.user.dto.StatusDto;
import mx.edu.utez.integradoraHugo.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return service.findAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> changeStatus(@PathVariable Long id, @Valid @RequestBody StatusDto dto) {

        return service.changeStatus(id, dto.toEntity());
    }
}
