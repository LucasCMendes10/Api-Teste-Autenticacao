package autenticacao.teste.apiautenticacao.service;

import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

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

        var role = mock(Role.class);
        var name = role.getName();

        when(roleRepository.findByName(name)).thenReturn(role);

        assertEquals(role, roleService.findByName(name));
    }
}
