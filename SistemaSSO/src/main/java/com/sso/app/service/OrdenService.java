package com.sso.app.service;

import com.sso.app.entity.CorrelativoOrden;
import com.sso.app.entity.Equipo;
import com.sso.app.entity.Orden;
import com.sso.app.entity.TipoEquipo;
import com.sso.app.repository.CorrelativoOrdenRepository;
import com.sso.app.repository.EquipoRepository;
import com.sso.app.repository.OrdenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdenService {
    private final OrdenRepository ordenRepository;
    private final EquipoRepository equipoRepository;
    private final CorrelativoOrdenRepository correlativoOrdenRepository;

    public List<Orden> findAllActive(){
        return (List<Orden>) this.ordenRepository.findAllActive();
    }
    public Optional<Orden> findById(Long id) {
        return this.ordenRepository.findById(id);
    }
    public Optional<Orden> findByNumeroOT(String numeroOT) {
        return this.ordenRepository.findByNumeroOT(numeroOT);
    }

    @Transactional
    public Orden save(Orden orden) {
        // Verificar si el equipo existe
        Equipo equipo = equipoRepository.findById(orden.getEquipo().getId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        TipoEquipo tipoEquipo = equipo.getTipoEquipo();
        if (tipoEquipo == null) {
            throw new RuntimeException("El equipo no tiene asociado un tipo de equipo");
        }

        // Asignar el equipo existente a la orden
        orden.setEquipo(equipo);

        // Obtener tipo transformado
        String tipoFormateado = mapearTipo(tipoEquipo.getTipo());

        // Generar número OT
        String numeroOT = generarNumeroOT(tipoFormateado);
        orden.setNumeroOT(numeroOT);

        orden.setEquipo(equipo);

        return this.ordenRepository.save(orden);
    }
    private String mapearTipo(String tipoOriginal) {
        return switch (tipoOriginal.toUpperCase()) {
            case "PCP" -> "PCP-DH";
            case "UCL" -> "RRL-UCL";
            case "BM"  -> "RRL-SRP";
            case "FG"  -> "TRL-OTH";
            default -> tipoOriginal; // o throw new RuntimeException("Tipo no reconocido: " + tipoOriginal);
        };
    }

    @Transactional
    public String generarNumeroOT(String tipoFormateado) {
        LocalDateTime now = LocalDateTime.now();
        int anio = now.getYear();
        int mes = now.getMonthValue();

        // Buscar correlativo o crearlo si no existe
        CorrelativoOrden correlativo = correlativoOrdenRepository.findByTipo(tipoFormateado)
                .orElseGet(() -> {
                    CorrelativoOrden nuevo = new CorrelativoOrden();
                    nuevo.setTipo(tipoFormateado);
                    nuevo.setUltimoNumero(0);
                    return nuevo;
                });

        int siguienteNumero = correlativo.getUltimoNumero() + 1;
        correlativo.setUltimoNumero(siguienteNumero);
        correlativoOrdenRepository.save(correlativo);

        return tipoFormateado + "-" + anio + "-" + String.format("%02d", mes) +  "-" + String.format("%06d", siguienteNumero);
    }

    //sofDeleted para Orden
    public void deleteById(Long id){
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setEliminado(true);
        this.ordenRepository.save(orden);
    }

    public List<Orden> getActiveOrdenesByClienteId(Long id){
        return this.ordenRepository.findAllActiveByClienteId(id);
    }

}
