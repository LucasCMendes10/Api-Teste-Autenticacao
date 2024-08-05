package autenticacao.teste.apiautenticacao.service;

import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @DisplayName("save deve retornar um User que acaba de ser cadastrado banco, recebendo como argumento um User para" +
            "ser cadastrado")
    @Test
    void testSave() {

        var user = mock(User.class);

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.save(user));
    }

    @DisplayName("findAll deve retornar uma lista todos os Users cadastrado no banco")
    @Test
    void testFindAll() {

        var users = List.of(mock(User.class));

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userService.findAll());
    }

    @DisplayName("findByUsername deve retornar um Optional de User cadastrado no banco, de acordo com o username " +
            "recebido como argumento")
    @Test
    void testFindByUsername() {

        var user = Optional.of(mock(User.class));
        var username = user.get().getUsername();

        when(userRepository.findByUsername(username)).thenReturn(user);

        assertEquals(user, userService.findByUsername(username));
    }
}
