package mx.edu.utez.integradoraHugo.services.role;

import mx.edu.utez.integradoraHugo.config.ApiResponse;
import mx.edu.utez.integradoraHugo.models.role.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(
                new ApiResponse(repository.findAll(), HttpStatus.OK),
                HttpStatus.OK
        );
    }
}
