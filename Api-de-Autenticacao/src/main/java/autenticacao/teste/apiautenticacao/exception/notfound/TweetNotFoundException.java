package autenticacao.teste.apiautenticacao.exception.notfound;

public class TweetNotFoundException extends NotFoundException {

    public static final String MESSAGE = "Tweet não encontrado";

    public TweetNotFoundException() {
        super(MESSAGE);
    }

    public TweetNotFoundException(String message) {
        super(message);
    }

    public TweetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
