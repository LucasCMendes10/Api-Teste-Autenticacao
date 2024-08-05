package autenticacao.teste.apiautenticacao.dto.mapper;

import autenticacao.teste.apiautenticacao.dto.TweetRequestDto;
import autenticacao.teste.apiautenticacao.dto.TweetResponseDto;
import autenticacao.teste.apiautenticacao.model.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TweetMapper {

    Tweet toDomain(TweetRequestDto dto);

    TweetResponseDto toResponseDto(Tweet tweet);
}
