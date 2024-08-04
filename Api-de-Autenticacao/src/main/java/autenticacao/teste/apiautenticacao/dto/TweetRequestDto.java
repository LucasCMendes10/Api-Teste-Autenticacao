package autenticacao.teste.apiautenticacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TweetRequestDto {

    @NotBlank
    private String content;
}
