package br.fiap.cliente.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aluno")
@Data
public class Atividade {
    @Id
    private String id;
    private Integer rm;
    private String nome;
    private String urlGitHub;
    private Integer numeroAtividade;
}
