package com.sso.app.service;

import com.sso.app.entity.TipoEquipo;
import com.sso.app.repository.TipoEquipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoEquipoService {
    //Buscamos por tipo y/o marca
    private final TipoEquipoRepository equipoRepository;

    public TipoEquipo addEquipos(TipoEquipo equipo){
        return this.equipoRepository.save(equipo);
    }
    public List<TipoEquipo> findByModelo(String modelo){
        return this.equipoRepository.findByModelo(modelo);
    }
    public List<TipoEquipo> findAll(){
        return (List<TipoEquipo>) this.equipoRepository.findAll();
    }

    //C
    public void deleteById (Long id){
        equipoRepository.deleteById(id);
    }
}
