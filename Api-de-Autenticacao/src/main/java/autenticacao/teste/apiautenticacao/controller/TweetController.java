package autenticacao.teste.apiautenticacao.controller;

import autenticacao.teste.apiautenticacao.dto.FeedItemDto;
import autenticacao.teste.apiautenticacao.dto.FeedResponseDto;
import autenticacao.teste.apiautenticacao.dto.TweetRequestDto;
import autenticacao.teste.apiautenticacao.dto.TweetResponseDto;
import autenticacao.teste.apiautenticacao.dto.mapper.TweetMapper;
import autenticacao.teste.apiautenticacao.model.Role;
import autenticacao.teste.apiautenticacao.model.Tweet;
import autenticacao.teste.apiautenticacao.model.User;
import autenticacao.teste.apiautenticacao.repository.TweetRepository;
import autenticacao.teste.apiautenticacao.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TweetMapper tweetMapper;

    @PostMapping
    public ResponseEntity<TweetResponseDto> save(
            @RequestBody @Valid TweetRequestDto dto,
            JwtAuthenticationToken token
    ) {

        Optional<User> user = userRepository.findByUsername(token.getName());

        Tweet tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(dto.getContent());

        tweetRepository.save(tweet);

        return ResponseEntity.status(201).body(tweetMapper.toResponseDto(tweet));
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

    @GetMapping("/feed")
    public ResponseEntity<FeedResponseDto> feed(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {

        Page<FeedItemDto> tweets = tweetRepository.findAll(
                PageRequest.of(page, pageSize, Sort.Direction.DESC, "dtCriacao"))
                .map(tweet -> new FeedItemDto(tweet.getId(), tweet.getContent(), tweet.getUser().getUsername()));

        return ResponseEntity.status(200).body(FeedResponseDto.builder()
                        .feedItens(tweets.getContent())
                        .page(page)
                        .pageSize(pageSize)
                        .totalPages(tweets.getTotalPages())
                        .totalElements(tweets.getTotalElements())
                        .build());
    }
}
