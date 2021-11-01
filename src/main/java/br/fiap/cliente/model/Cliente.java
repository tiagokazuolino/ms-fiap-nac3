package br.fiap.cliente.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    @Id
    private String id;
    private String nome;
    private String email;
    private String password;
    private Date criadoEm;
    private Date atualizadoEm;
}
