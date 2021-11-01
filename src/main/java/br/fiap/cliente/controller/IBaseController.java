package br.fiap.cliente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IBaseController<T> {
    ResponseEntity<T> post(@RequestBody T data);
    ResponseEntity<T> put(@RequestParam String id, @RequestBody T data);
    ResponseEntity<Boolean> delete(@RequestBody String id);
    ResponseEntity<T> get(@RequestBody String id);
    ResponseEntity<List<T>> getAll();
}
