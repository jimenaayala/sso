package com.sso.app.controller;

import com.sso.app.entity.Salida;
import com.sso.app.service.SalidaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/salida")
@AllArgsConstructor
@CrossOrigin
public class SalidaController extends BaseController<Salida> {
    private SalidaService salidaService;
    @Override
    protected List<Salida> findAllActive(){
        return salidaService.findAllActive();
    }

    @Override
    protected Optional<Salida> findById(Long id) {
        return salidaService.findById(id);
    }

    @Override
    protected Salida save(Salida entity) {
        return salidaService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        salidaService.deleteById(id);
    }

    @Override
    protected void setId(Salida entity, Long id) {
        entity.setId(id);
    }

}
