package autenticacao.teste.apiautenticacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FeedItemDto {

    private Long id;
    private String content;
    private String username;
}
