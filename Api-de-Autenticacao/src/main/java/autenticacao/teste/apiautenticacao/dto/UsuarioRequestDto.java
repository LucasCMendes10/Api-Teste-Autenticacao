package autenticacao.teste.apiautenticacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDto {

    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
