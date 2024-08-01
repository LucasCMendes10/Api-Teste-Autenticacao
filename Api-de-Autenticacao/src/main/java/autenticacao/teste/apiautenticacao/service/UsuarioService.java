package autenticacao.teste.apiautenticacao.service;

import autenticacao.teste.apiautenticacao.model.Usuario;
import autenticacao.teste.apiautenticacao.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Transactional
    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
