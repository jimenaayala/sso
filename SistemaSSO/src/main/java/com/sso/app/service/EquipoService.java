package com.sso.app.service;

import com.sso.app.entity.Equipo;
import com.sso.app.repository.EquipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipoService {
    //Buscamos por tipo y/o marca
    private final EquipoRepository equipoRepository;

    public Equipo addEquipos(Equipo equipo){
        return this.equipoRepository.save(equipo);
    }
    public List<Equipo> findByModelo(String modelo){
        return this.equipoRepository.findByModelo(modelo);
    }
    public List<Equipo> findAll(){
        return (List<Equipo>) this.equipoRepository.findAll();
    }
    public void deleteById (Long id){
        equipoRepository.deleteById(id);
    }
}
