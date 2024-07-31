package autenticacao.teste.apiautenticacao.dto;

import lombok.Data;

@Data
public class UsuarioResponseDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
}
