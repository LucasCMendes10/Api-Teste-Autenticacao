package autenticacao.teste.apiautenticacao.repository;

import autenticacao.teste.apiautenticacao.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
