package autenticacao.teste.apiautenticacao.dto;

import lombok.Data;

@Data
public class UsuarioRequestDto {

    private String nome;
    private String email;
    private String senha;
}
