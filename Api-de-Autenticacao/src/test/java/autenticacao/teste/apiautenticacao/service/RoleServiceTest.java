package autenticacao.teste.apiautenticacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        roleService = new RoleService(roleRepository);
    }

    @DisplayName("findByName deve retornar uma única Role cadastrada no banco, de acordo com um nome passado " +
            "com argumento")
    @Test
    void testFindByName() {

        Role role = mock(Role.class);
        String name = role.getName();

        when(roleRepository.findByName(name)).thenReturn(role);

        assertEquals(role, roleService.findByName(name));
    }
}
