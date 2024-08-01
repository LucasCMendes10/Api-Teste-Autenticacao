package autenticacao.teste.apiautenticacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        Long id;

        Values(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }
}
