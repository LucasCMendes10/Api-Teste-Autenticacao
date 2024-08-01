package autenticacao.teste.apiautenticacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private Long expiresIn; // tempo de expiração do token
}
