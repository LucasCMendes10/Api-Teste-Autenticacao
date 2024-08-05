package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.UserRequestDto;
import autenticacao.teste.apiautenticacao.dto.UserResponseDto;
import autenticacao.teste.apiautenticacao.dto.mapper.UserMapper;
import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.RoleRepository;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserRequestDto dto) {

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

        return ResponseEntity.status(201).body(userMapper.toResponseDto(newUser));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')") // o valor é por causa de como está escrito no jwt
    public ResponseEntity<List<UserResponseDto>> findAll() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(users.stream()
                .map(userMapper::toResponseDto)
                .toList());
    }
}
