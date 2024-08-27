package com.sso.app.controller;

import com.sso.app.entity.Equipo;
import com.sso.app.service.EquipoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/equipo")
@AllArgsConstructor
@CrossOrigin
public class EquipoController extends BaseController<Equipo> {
    private EquipoService equipoService;

    @Override
    protected List<Equipo> findAllActive(){
        return equipoService.findAllActive();
    }
    @Override
    protected Optional<Equipo> findById(Long id) {
        return equipoService.findById(id);
    }

    @Override
    protected Equipo save(Equipo entity) {
        return equipoService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        equipoService.deleteById(id);
    }

    @Override
    protected void setId(Equipo entity, Long id) {
        entity.setId(id);
    }
}
