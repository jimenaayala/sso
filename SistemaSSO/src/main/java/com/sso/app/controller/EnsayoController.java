package com.sso.app.controller;

import com.sso.app.entity.Ensayo;
import com.sso.app.service.EnsayoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;  // Importación necesaria
import java.util.Optional;  // Importación necesaria

@RestController
@RequestMapping("api/ensayo")
@AllArgsConstructor
@CrossOrigin

public class EnsayoController extends BaseController<Ensayo> {
    private EnsayoService ensayoService;

    @Override
    protected List<Ensayo> findAllActive(){
        return ensayoService.findAllActive();
    }
    @Override
    protected Optional<Ensayo> findById(Long id) {
        return ensayoService.findById(id);
    }

    @Override
    protected Ensayo save(Ensayo entity) {
        return ensayoService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        ensayoService.deleteById(id);
    }

    @Override
    protected void setId(Ensayo entity, Long id) {
        entity.setId(id);
    }



}
