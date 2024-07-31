package autenticacao.teste.apiautenticacao.repository;

import autenticacao.teste.apiautenticacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
