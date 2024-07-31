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
import java.util.Objects;

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

        List<Usuario> usuarios = usuarioService.listarTodos();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarios.stream()
                .map(usuarioMapper::toResponseDto)
                .toList());
    }

    @GetMapping("/login")
    public ResponseEntity<UsuarioResponseDto> login(@RequestParam String email, @RequestParam String senha) {

        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(404).build();
        }

        boolean senhaValida = usuario.getSenha().equals(senha);

        if (!senhaValida) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.status(200).body(usuarioMapper.toResponseDto(usuario));
    }
}
