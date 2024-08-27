package com.sso.app.controller;

import com.sso.app.entity.TipoEquipo;
import com.sso.app.service.TipoEquipoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tipoequipo")
@AllArgsConstructor
@CrossOrigin
public class TipoEquipoController extends BaseController<TipoEquipo> {
    private final TipoEquipoService tipoEquipoService;

    @Override
    protected List<TipoEquipo> findAllActive(){
        return tipoEquipoService.findAllActive();
    }

    @Override
    protected Optional<TipoEquipo> findById(Long id) {
        return tipoEquipoService.findById(id);
    }

    @Override
    protected TipoEquipo save(TipoEquipo entity) {
        return tipoEquipoService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        tipoEquipoService.deleteById(id);
    }

    @Override
    protected void setId(TipoEquipo entity, Long id) {
        entity.setId(id);
    }


}
