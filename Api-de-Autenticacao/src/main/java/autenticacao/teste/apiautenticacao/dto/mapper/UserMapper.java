package autenticacao.teste.apiautenticacao.dto.mapper;

import autenticacao.teste.apiautenticacao.dto.UserRequestDto;
import autenticacao.teste.apiautenticacao.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toDomain(UserRequestDto dto);
}
