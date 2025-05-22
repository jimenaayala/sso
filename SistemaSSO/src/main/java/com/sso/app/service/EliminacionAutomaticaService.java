package com.sso.app.service;

import com.sso.app.entity.Imagen;
import com.sso.app.entity.Orden;
import com.sso.app.entity.Recepcion;
import com.sso.app.repository.OrdenRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EliminacionAutomaticaService {
/*
    private final OrdenRepository ordenRepository;
    private final ImagenRepository imagenRepository;
    private final ImagenService imagenService;

    @Value("${imagenes.diasParaBorrar}") // Configurable en application.properties
    private int diasParaBorrar;

    public EliminacionAutomaticaService(OrdenRepository ordenRepository, ImagenRepository imagenRepository, ImagenService imagenService) {
        this.ordenRepository = ordenRepository;
        this.imagenRepository = imagenRepository;
        this.imagenService = imagenService;
    }

    @Scheduled(cron = "0 0 2 * * ?") // Se ejecuta todos los días a las 2 AM
    public void eliminarImagenesAntiguas() {
        LocalDate fechaLimite = LocalDate.now().minusDays(diasParaBorrar);
        List<Orden> ordenes = ordenRepository.findBySalidaTrueAndFechaSalidaBefore(fechaLimite);

        for (Orden orden : ordenes) {
            //Recepcion recepcion = orden.getRecepcion();
            //solo puesto para probrar prolema de estructura, luego borrar proxima linea y dejar la de arriba
            Recepcion recepcion = new Recepcion();
            eliminarImagenes(recepcion.getImagenes());

//            for (Inspeccion inspeccion : orden.getInspeccion()) {
//                eliminarImagenes(inspeccion.getImagenes());
//            }
//            for (Ensayo ensayo : orden.getEnsayo()) {
//                eliminarImagenes(ensayo.getImagenes());
//            }
//            for (Salida salida : orden.getSalida()) {
//                eliminarImagenes(salida.getImagenes());
//            }
        }
    }

    private void eliminarImagenes(List<Imagen> imagenes) {
        for (Imagen imagen : imagenes) {
            try {
                imagenService.eliminarImagen(imagen.getUrl());
                imagenRepository.delete(imagen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
