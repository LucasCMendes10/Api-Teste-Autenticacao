package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.UserRequestDto;
import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.RoleRepository;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserRequestDto dto) {

        Role basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        Optional<User> userDb = userRepository.findByUsername(dto.getUsername());

        if (userDb.isPresent()) {
            return ResponseEntity.status(422).build();
        }

        User newUser= new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        newUser.setRoles(List.of(basicRole));

        userRepository.save(newUser);

        return ResponseEntity.status(201).build();
    }
}
