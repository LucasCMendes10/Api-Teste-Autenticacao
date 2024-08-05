package autenticacao.teste.apiautenticacao.model;

import autenticacao.teste.apiautenticacao.dto.LoginRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    public boolean isLoginCorrect(LoginRequestDto dto, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(dto.getPassword(), this.password);
    }
}
