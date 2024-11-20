package com.sso.app.service;

import com.sso.app.entity.ItemDetailRecepcion;
import com.sso.app.entity.ItemRecepcion;
import com.sso.app.entity.Recepcion;

import com.sso.app.repository.RecepcionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class RecepcionService {

    private final RecepcionRepository recepcionRepository;

    /**
     * Guarda o actualiza una Recepcion de forma parcial, permitiendo persistir
     * progresivamente a medida que se llenan los datos.
     */
    @Transactional
    public Recepcion guardarOActualizarRecepcion(Recepcion recepcion) {
        // Verificar si la Recepcion ya existe en la base de datos
        if (recepcion.getId() != null) {
            // Cargar la Recepcion existente desde la base de datos
            Optional<Recepcion> recepcionExistenteOpt = recepcionRepository.findById(recepcion.getId());

            if (recepcionExistenteOpt.isPresent()) {
                Recepcion recepcionExistente = recepcionExistenteOpt.get();

                // Actualizar solo los campos que no están nulos en la nueva Recepcion
                actualizarCampos(recepcionExistente, recepcion);

                // Guardar la Recepcion actualizada en la base de datos
                return recepcionRepository.save(recepcionExistente);
            }
        }

        // Si es una nueva Recepcion o no existe en la base de datos, guardar directamente
        return recepcionRepository.save(recepcion);
    }

    /**
     * Actualiza los campos de recepcionDestino con los valores no nulos de recepcionOrigen.
     */
    @Transactional
    private void actualizarCampos(Recepcion recepcionDestino, Recepcion recepcionOrigen) {
        // Actualizar campos principales usando Optional para evitar nulls
        Optional.ofNullable(recepcionOrigen.getComentario()).ifPresent(recepcionDestino::setComentario);
        Optional.ofNullable(recepcionOrigen.isEliminado()).ifPresent(eliminado -> {
            if (eliminado) recepcionDestino.setEliminado(true);
        });

        // Actualizar ItemRecepcion y sus detalles de forma progresiva
        Optional.ofNullable(recepcionOrigen.getItemRecepcion()).ifPresent(itemOrigen -> {
            ItemRecepcion itemDestino = recepcionDestino.getItemRecepcion();

            // Para cada campo en itemOrigen, actualizar en itemDestino si está presente
            Optional.ofNullable(itemOrigen.getCubreGrampa()).ifPresent(cubreGrampa ->
                    itemDestino.setCubreGrampa(actualizarDetalle(itemDestino.getCubreGrampa(), cubreGrampa))
            );
            Optional.ofNullable(itemOrigen.getCubrePolea()).ifPresent(cubrePolea ->
                    itemDestino.setCubrePolea(actualizarDetalle(itemDestino.getCubrePolea(), cubrePolea))
            );
            Optional.ofNullable(itemOrigen.getCubreVastago()).ifPresent(cubreVastago ->
                    itemDestino.setCubreVastago(actualizarDetalle(itemDestino.getCubreVastago(), cubreVastago))
            );
            Optional.ofNullable(itemOrigen.getGrampaAntiEyeccion()).ifPresent(grampaAntiEyeccion ->
                    itemDestino.setGrampaAntiEyeccion(actualizarDetalle(itemDestino.getGrampaAntiEyeccion(), grampaAntiEyeccion))
            );
            Optional.ofNullable(itemOrigen.getEstructuraChasis()).ifPresent(estructuraChasis ->
                    itemDestino.setEstructuraChasis(actualizarDetalle(itemDestino.getEstructuraChasis(), estructuraChasis))
            );
            Optional.ofNullable(itemOrigen.getLinternaSeparador()).ifPresent(linternaSeparador ->
                    itemDestino.setLinternaSeparador(actualizarDetalle(itemDestino.getLinternaSeparador(), linternaSeparador))
            );
            Optional.ofNullable(itemOrigen.getMesaDeMotor()).ifPresent(mesaDeMotor ->
                    itemDestino.setMesaDeMotor(actualizarDetalle(itemDestino.getMesaDeMotor(), mesaDeMotor))
            );
            Optional.ofNullable(itemOrigen.getRielesDeMotor()).ifPresent(rielesDeMotor ->
                    itemDestino.setRielesDeMotor(actualizarDetalle(itemDestino.getRielesDeMotor(), rielesDeMotor))
            );
            Optional.ofNullable(itemOrigen.getSoporteDeTransporte()).ifPresent(soporteDeTransporte ->
                    itemDestino.setSoporteDeTransporte(actualizarDetalle(itemDestino.getSoporteDeTransporte(), soporteDeTransporte))
            );
            Optional.ofNullable(itemOrigen.getPoleaConducida()).ifPresent(poleaConducida ->
                    itemDestino.setPoleaConducida(actualizarDetalle(itemDestino.getPoleaConducida(), poleaConducida))
            );
        });
    }

    /**
     * Actualiza los campos no nulos de detalleDestino con los valores de detalleOrigen.
     */
    @Transactional
    private ItemDetailRecepcion actualizarDetalle(ItemDetailRecepcion detalleDestino, ItemDetailRecepcion detalleOrigen) {
        if (detalleDestino == null) {
            detalleDestino = new ItemDetailRecepcion(); // Crear un nuevo detalle si no existe
        }

        // Actualizar campos usando Optional y lambdas
        Optional.ofNullable(detalleOrigen.getRequerimiento()).ifPresent(detalleDestino::setRequerimiento);
        Optional.ofNullable(detalleOrigen.getObservacion()).ifPresent(detalleDestino::setObservacion);
        detalleDestino.setEstado(detalleOrigen.isEstado()); // Siempre actualiza el estado

        return detalleDestino;
    }

    @Transactional
    public void deletedById(Long id) {
        Recepcion recepcion = recepcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepción no encontrada"));
        recepcion.setEliminado(true); // Marcar como eliminado
        recepcionRepository.save(recepcion); // Guardar los cambios
    }

    @Transactional
    public Recepcion buscarPorId(Long id) {
        return recepcionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recepción no encontrada con id: " + id));
    }

}


