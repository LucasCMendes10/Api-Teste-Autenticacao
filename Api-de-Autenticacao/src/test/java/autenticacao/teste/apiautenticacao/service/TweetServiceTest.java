package autenticacao.teste.apiautenticacao.service;

import autenticacao.teste.apiautenticacao.exception.notfound.TweetNotFoundException;
import autenticacao.teste.apiautenticacao.model.Tweet;
import autenticacao.teste.apiautenticacao.repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {

    @Mock
    private TweetRepository tweetRepository;

    private TweetService tweetService;

    @BeforeEach
    void setUp() {
        tweetService = new TweetService(tweetRepository);
    }

    @DisplayName("save deve retornar um Tweet que foi cadastrado no banco, recebendo como argumento o Tweet que " +
            "será cadastrado")
    @Test
    void testSave() {

        var tweet = mock(Tweet.class);

        when(tweetRepository.save(tweet)).thenReturn(tweet);

        assertEquals(tweet, tweetService.save(tweet));
    }

    @DisplayName("findAllPage deve retornar uma Page com todos os Tweets cadastrados no banco, recebendo como " +
            "argumento um PageRequest")
    @Test
    void testFindAllPage() {

        var tweet = mock(Tweet.class);
        var tweets = new PageImpl<>(Collections.singletonList(tweet));
        var pageRequest = mock(PageRequest.class);

        when(tweetRepository.findAll(pageRequest)).thenReturn(tweets);

        assertEquals(tweets, tweetService.findAllPage(pageRequest));
    }

    @DisplayName("findById deve retornar um Optional de Tweet cadastrado no banco, recebendo um id de Tweet " +
            "como argumento")
    @Test
    void testFindById() {

        var tweet = Optional.of(mock(Tweet.class));
        var id = tweet.get().getId();

        when(tweetRepository.findById(id)).thenReturn(tweet);

        assertEquals(tweet, tweetService.findById(id));
    }

    @DisplayName("deleteById não deve retonar nada, mas verificar se o User foi deletado do banco, " +
            "recebendo como argumento o id do User que deverá ser deletado")
    @Test
    void testDeleteById() {

        var tweet = mock(Tweet.class);
        var id = tweet.getId();

        when(tweetRepository.existsById(id)).thenReturn(true);

        tweetService.deleteById(id);

        verify(tweetRepository).deleteById(id);
    }

    @DisplayName("deleteById deve retonar uma exception de Not Found própria do Tweet, caso ele não seja " +
            "encontrado no banco, recebendo como argumento um id de tweet")
    @Test
    void testDeleteByIdNotFound() {

        var tweet = mock(Tweet.class);
        var id = tweet.getId();

        when(tweetRepository.existsById(id)).thenReturn(false);

        assertThrows(TweetNotFoundException.class, () -> tweetService.deleteById(id));
    }
}
