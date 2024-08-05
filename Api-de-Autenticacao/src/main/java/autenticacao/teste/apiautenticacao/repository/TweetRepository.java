package autenticacao.teste.apiautenticacao.repository;

import autenticacao.teste.apiautenticacao.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
