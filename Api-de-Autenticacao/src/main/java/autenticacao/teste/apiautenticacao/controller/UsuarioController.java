package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.UsuarioRequestDto;
import autenticacao.teste.apiautenticacao.dto.UsuarioResponseDto;
import autenticacao.teste.apiautenticacao.dto.mapper.UsuarioMapper;
import autenticacao.teste.apiautenticacao.model.Usuario;
import autenticacao.teste.apiautenticacao.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioDto) {

        Usuario usuario = usuarioMapper.toDomain(usuarioDto);
        Usuario usuarioSalvo = usuarioService.salvar(usuario);

        return ResponseEntity.status(201).body(usuarioMapper.toResponseDto(usuarioSalvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {

        return ResponseEntity.status(200).body(usuarioService.listarTodos().stream()
                .map(usuarioMapper::toResponseDto)
                .toList());
    }
}
