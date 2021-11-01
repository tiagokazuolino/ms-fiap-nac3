package br.fiap.cliente.controller;

import br.fiap.cliente.model.Cliente;
import br.fiap.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController implements IBaseController<Cliente>{
    @Autowired
    ClienteRepository repository;

    @Override
    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente data) {
        try{
            if(data == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Cliente entity = repository.save(data);
            return new ResponseEntity<Cliente>(entity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> put(@RequestParam String id,@RequestBody  Cliente data) {
        try{
            if(data == null || id.isEmpty()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Cliente entity = repository.findById(id).orElse(null);
            if(entity == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            entity = data;
            entity = repository.save(entity);
            return new ResponseEntity<Cliente>(entity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestParam String id) {
        try{
            if(id.isEmpty()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Cliente entity = repository.findById(id).orElse(null);
            if(entity == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            repository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@RequestParam String id) {
        try{
            if(id.isEmpty()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Cliente entity = repository.findById(id).orElse(null);
            if(entity == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        try{
            List<Cliente> entities = repository.findAll();
            return new ResponseEntity<>(entities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
