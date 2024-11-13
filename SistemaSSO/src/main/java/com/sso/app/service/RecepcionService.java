package com.sso.app.service;

import com.sso.app.entity.ItemDetailRecepcion;
import com.sso.app.entity.ItemRecepcion;
import com.sso.app.entity.Recepcion;
import com.sso.app.repository.ItemDetailRecepcionRepository;
import com.sso.app.repository.ItemRecepcionRepository;
import com.sso.app.repository.RecepcionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecepcionService {

    private final RecepcionRepository recepcionRepository;


//    public Recepcion save (Recepcion recepcion){
//        return this.recepcionRepository.save(recepcion);
//    }
//    public List<Recepcion> findAllActive(){
//        return this.recepcionRepository.findAllActive();
//    }
//    public Optional<Recepcion> findById (Long id){
//        return this.recepcionRepository.findById(id);
//    }
//    public void deleteById(Long id){
//        Recepcion recepcion = this.recepcionRepository.findById(id).orElseThrow(() -> new RuntimeException("Recepción no encontrada"));
//        recepcion.setEliminado(true);
//        this.recepcionRepository.save(recepcion);
//    }

    public Recepcion crearRecepcion(Recepcion recepcion) {
        // Verificar que todos los detalles de ItemRecepcion no sean nulos
        ItemRecepcion itemRecepcion = Optional.ofNullable(recepcion.getItemRecepcion())
                .orElseThrow(() -> new IllegalArgumentException("ItemRecepcion no puede ser nulo"));

        // Construir el objeto ItemRecepcion asegurando que los detalles no sean nulos
        ItemRecepcion newItemRecepcion = ItemRecepcion.builder()
                .cubreGrampa(Optional.ofNullable(itemRecepcion.getCubreGrampa()).orElse(new ItemDetailRecepcion()))
                .cubrePolea(Optional.ofNullable(itemRecepcion.getCubrePolea()).orElse(new ItemDetailRecepcion()))
                .cubreVastago(Optional.ofNullable(itemRecepcion.getCubreVastago()).orElse(new ItemDetailRecepcion()))
                .grampaAntiEyeccion(Optional.ofNullable(itemRecepcion.getGrampaAntiEyeccion()).orElse(new ItemDetailRecepcion()))
                .estructuraChasis(Optional.ofNullable(itemRecepcion.getEstructuraChasis()).orElse(new ItemDetailRecepcion()))
                .linternaSeparador(Optional.ofNullable(itemRecepcion.getLinternaSeparador()).orElse(new ItemDetailRecepcion()))
                .mesaDeMotor(Optional.ofNullable(itemRecepcion.getMesaDeMotor()).orElse(new ItemDetailRecepcion()))
                .rielesDeMotor(Optional.ofNullable(itemRecepcion.getRielesDeMotor()).orElse(new ItemDetailRecepcion()))
                .soporteDeTransporte(Optional.ofNullable(itemRecepcion.getSoporteDeTransporte()).orElse(new ItemDetailRecepcion()))
                .poleaConducida(Optional.ofNullable(itemRecepcion.getPoleaConducida()).orElse(new ItemDetailRecepcion()))
                .build();

        Recepcion nuevaRecepcion = Recepcion.builder()
                .itemRecepcion(newItemRecepcion)
                .comentario(recepcion.getComentario())
                .eliminado(false)  // Asegurar el valor inicial del campo eliminado
                .build();

        return recepcionRepository.save(nuevaRecepcion);
    }

}
