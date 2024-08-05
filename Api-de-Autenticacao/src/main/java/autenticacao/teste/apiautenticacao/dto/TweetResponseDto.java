package autenticacao.teste.apiautenticacao.dto;

import autenticacao.teste.apiautenticacao.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TweetResponseDto {

    private Long id;
    private User user;
    private String content;
    private LocalDateTime dtCriacao;
}
