package autenticacao.teste.apiautenticacao.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedResponseDto {

    private List<FeedItemDto> feedItens;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
}
