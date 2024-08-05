package autenticacao.teste.apiautenticacao.service;

import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
