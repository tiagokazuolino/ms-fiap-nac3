package br.fiap.cliente.controller;

import br.fiap.cliente.model.Atividade;
import br.fiap.cliente.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividade")
public class AtividadeController{
    @Autowired
    AtividadeRepository repository;


    @PostMapping
    public ResponseEntity<Atividade> post(@RequestBody Atividade data) {
        try{
         if(data == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
         Atividade entity = repository.save(data);
         return new ResponseEntity<Atividade>(entity, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")

    public ResponseEntity<Atividade> put(@PathVariable String id, @RequestBody Atividade data) {
        try {
            if (data == null || id.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Optional<Atividade> entity = repository.findById(id);
            if (!entity.isPresent()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            entity.map(e -> {
                e.setId(id);
                e.setNumeroAtividade(data.getNumeroAtividade());
                e.setNome(data.getNome());
                e.setRm(data.getRm());
                e.setUrlGitHub(data.getUrlGitHub());
                return e;
            });
            Atividade response = repository.save(entity.get());
            return new ResponseEntity<Atividade>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        try{
            if(id.isEmpty()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Atividade entity = repository.findById(id).orElse(null);
            if(entity == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            repository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Atividade> get(@PathVariable String id) {
        try{
            if(id.isEmpty()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Atividade entity = repository.findById(id).orElse(null);
            if(entity == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> getAll() {
        try{
            List<Atividade> entities = repository.findAll();
            return new ResponseEntity<>(entities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
