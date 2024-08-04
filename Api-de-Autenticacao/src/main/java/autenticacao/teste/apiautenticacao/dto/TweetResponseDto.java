package autenticacao.teste.apiautenticacao.dto;

import autenticacao.teste.apiautenticacao.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TweetResponseDto {

    private UUID id;
    private User user;
    private String content;
    private LocalDateTime dtCriacao;
}
