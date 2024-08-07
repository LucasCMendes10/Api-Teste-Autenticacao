package autenticacao.teste.apiautenticacao.config;

// Essa classe serve de exemplo de como criar uma anotação personalizada de Spring Security

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Constraint(validatedBy = {Validator.class}) // chama uma classe que vc cria com a regra de validação
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAuthentication {

    // Coloca os atributos necessários da classe
}
