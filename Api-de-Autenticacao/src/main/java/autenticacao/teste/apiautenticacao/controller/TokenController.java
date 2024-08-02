package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.LoginRequestDto;
import autenticacao.teste.apiautenticacao.dto.LoginResponseDto;
import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto) {

        Optional<User> user = userRepository.findByUsername(dto.getUsername());

        if (user.isEmpty() || !user.get().isLoginCorrect(dto, bCryptPasswordEncoder)) {
            return ResponseEntity.status(401).build();
        }
        // A parte de cima autentica se o login está correto

        // A parte de baixo vai ser para gerar o token de acesso

        // Definindo tempo de expiração do token
        var now = Instant.now();
        var expiresIn = 300L; // está em segundos

        // Descobrindo qual é a role do usuário que está se autenticando, para dar permissionamento à ele
        var scopes = user.get().getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        // Será gerado em formato de JSON, com os campos seguindo uma nomenclatura de boa prática
        var claims = JwtClaimsSet.builder()
                .issuer("testeautenticacao") // nome da aplicação
                .subject(dto.getUsername()) // poderia ser o id
                .issuedAt(now) // quando o token foi emitido
                .expiresAt(now.plusSeconds(expiresIn)) // quando o token vai expirar
                .claim("scope", scopes)
                .build();

        // Aqui será usado as chaves de acesso
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDto(jwtValue, expiresIn));
    }
}
