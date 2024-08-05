package autenticacao.teste.apiautenticacao.dto;

import autenticacao.teste.apiautenticacao.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
}
