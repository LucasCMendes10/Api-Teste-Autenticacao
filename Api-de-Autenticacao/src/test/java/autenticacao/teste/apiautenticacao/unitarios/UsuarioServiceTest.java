package autenticacao.teste.apiautenticacao.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import autenticacao.teste.apiautenticacao.model.Usuario;
import autenticacao.teste.apiautenticacao.repository.UsuarioRepository;
import autenticacao.teste.apiautenticacao.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @DisplayName("Salvar deve retornar o usuário que foi cadastrado no banco")
    @Test
    void salvar() {

        var usuario = mock(Usuario.class);

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        assertEquals(usuario, usuarioService.salvar(usuario));
    }

    @DisplayName("ListarTodos deve retornar uma lista de todos os usuários cadastrados no banco")
    @Test
    void listarTodos() {

        var usuarios = List.of(mock(Usuario.class));

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        assertEquals(usuarios, usuarioService.listarTodos());
    }

    @DisplayName("BuscarPorEmail deve retonar um usuário cadastrado no banco, de acordo com o e-mail")
    @Test
    void buscarPorEmail() {

        var usuario = mock(Usuario.class);
        var email = usuario.getEmail();

        when(usuarioRepository.findByEmail(email)).thenReturn(usuario);

        assertEquals(usuario, usuarioService.buscarPorEmail(email));
    }
}
