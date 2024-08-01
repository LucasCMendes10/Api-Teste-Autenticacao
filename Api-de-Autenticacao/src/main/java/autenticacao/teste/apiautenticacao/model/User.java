package autenticacao.teste.apiautenticacao.model;

import autenticacao.teste.apiautenticacao.dto.LoginRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    public boolean isLoginCorrect(LoginRequestDto dto, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(dto.getPassword(), this.password);
    }
}
