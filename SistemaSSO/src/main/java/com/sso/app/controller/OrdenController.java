package com.sso.app.controller;

import com.sso.app.entity.Orden;
import com.sso.app.service.OrdenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/ordenes")
@AllArgsConstructor
@CrossOrigin
public class OrdenController extends BaseController<Orden>{

    private final OrdenService ordenService;

    @Override
    protected List<Orden> findAllActive(){
        return ordenService.findAllActive();
    }
    @Override
    protected Optional<Orden> findById(Long id) {
        return ordenService.findById(id);
    }

    @Override
    protected Orden save(Orden entity) {
        return ordenService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        ordenService.deleteById(id);
    }

    @Override
    protected void setId(Orden entity, Long id) {
        entity.setId(id);
    }


    @GetMapping("{id}/clientes")
    public ResponseEntity<List<Orden>> findAllActiveByClienteId(@PathVariable Long id) {
        List<Orden> ordenesClientes = ordenService.getActiveOrdenesByClienteId(id);
        return ResponseEntity.ok(ordenesClientes);
    }
}
