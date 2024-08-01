package autenticacao.teste.apiautenticacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
