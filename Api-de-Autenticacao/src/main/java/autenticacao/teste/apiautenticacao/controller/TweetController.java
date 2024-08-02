package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.TweetRequestDto;
import autenticacao.teste.apiautenticacao.dto.TweetResponseDto;
import autenticacao.teste.apiautenticacao.model.Tweet;
import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.TweetRepository;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
}
