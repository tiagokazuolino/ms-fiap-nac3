package br.fiap.cliente.repository;

import br.fiap.cliente.model.Atividade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtividadeRepository extends MongoRepository<Atividade, String> {
}

