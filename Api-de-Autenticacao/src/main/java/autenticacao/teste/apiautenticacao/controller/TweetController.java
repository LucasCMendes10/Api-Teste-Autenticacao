package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.TweetRequestDto;
import autenticacao.teste.apiautenticacao.dto.TweetResponseDto;
import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.model.Tweet;
import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.TweetRepository;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody @Valid TweetRequestDto dto,
            JwtAuthenticationToken token
    ) {

        Optional<User> user = userRepository.findByUsername(token.getName());

        Tweet tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(dto.getContent());

        tweetRepository.save(tweet);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, JwtAuthenticationToken token) {

        Tweet tweet = tweetRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Optional<User> user = userRepository.findByUsername(token.getName());

        boolean isAdmin = user.get().getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (!isAdmin && !tweet.getUser().getUsername().equals(token.getName())) {
            return ResponseEntity.status(403).build();
        }

        tweetRepository.deleteById(id);

        return ResponseEntity.status(204).build();
    }
}
