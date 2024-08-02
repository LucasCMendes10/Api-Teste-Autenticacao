package autenticacao.teste.apiautenticacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
