package com.sso.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List; // Importa List
import java.util.Optional; // Importa Optional

public abstract class BaseController<T> {

    protected abstract List<T> findAllActive();

    protected abstract Optional<T> findById(Long id);

    protected abstract T save(T entity);

    protected abstract void deleteById(Long id);

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = findAllActive();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        Optional<T> entity = findById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T savedEntity = save(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T entity) {
        // Suponiendo que la entidad tiene un método setId(Long id)
        setId(entity, id);
        T updatedEntity = save(entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        try {
            deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    protected abstract void setId(T entity, Long id);
}
