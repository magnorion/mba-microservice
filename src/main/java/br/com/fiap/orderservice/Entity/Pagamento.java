package br.com.fiap.orderservice.Entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    private int id;
    private int cartao;
    private String validade;
    private String bandeira;
}
