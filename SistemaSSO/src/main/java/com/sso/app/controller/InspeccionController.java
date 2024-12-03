package com.sso.app.controller;
import com.sso.app.controller.BaseController;

import com.sso.app.entity.Inspeccion;
import com.sso.app.service.InspeccionService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/inspeccion")
@AllArgsConstructor
@CrossOrigin
public class InspeccionController extends BaseController <Inspeccion>{

    private final InspeccionService inspeccionService;
    @Override
    protected List<Inspeccion> findAllActive(){
        return inspeccionService.findAllActive();
    }

    @Override
    protected Optional<Inspeccion> findById(Long id) {
        return inspeccionService.findById(id);
    }

    @Override
    protected Inspeccion save(Inspeccion entity) {
        return inspeccionService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        inspeccionService.deletedById(id);
    }

    @Override
    protected void setId(Inspeccion entity, Long id) {
        entity.setId(id);
    }



}
