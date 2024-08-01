package autenticacao.teste.apiautenticacao.dto.mapper;

import autenticacao.teste.apiautenticacao.dto.LoginRequestDto;
import autenticacao.teste.apiautenticacao.dto.UsuarioRequestDto;
import autenticacao.teste.apiautenticacao.dto.UsuarioResponseDto;
import autenticacao.teste.apiautenticacao.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    Usuario toDomain(UsuarioRequestDto dto);

    Usuario toDomain(LoginRequestDto dto);

    UsuarioResponseDto toResponseDto(Usuario usuario);
}
