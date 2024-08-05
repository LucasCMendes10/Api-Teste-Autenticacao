package autenticacao.teste.apiautenticacao.service;

import autenticacao.teste.apiautenticacao.exception.notfound.TweetNotFoundException;
import autenticacao.teste.apiautenticacao.model.Tweet;
import autenticacao.teste.apiautenticacao.repository.TweetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;

    @Transactional
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public Page<Tweet> findAllPage(PageRequest pageRequest) {
        return tweetRepository.findAll(pageRequest);
    }

    public Optional<Tweet> findById(Long id) {
        return tweetRepository.findById(id);
    }

    public void deleteById(Long id) {

        if (!tweetRepository.existsById(id)) {
            throw new TweetNotFoundException();
        }

        tweetRepository.deleteById(id);
    }
}
