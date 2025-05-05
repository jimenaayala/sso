package com.sso.app.repository;

import com.sso.app.entity.Orden;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends CrudRepository<Orden,Long> {
    Optional<Orden> findByNumeroOT(String numeroOT);

    //query para el softDeleted para Orden
    @Query("SELECT o FROM Orden o WHERE o.eliminado = false")
    List<Orden> findAllActive();

    @Query("SELECT o FROM Orden o WHERE o.eliminado = false AND o.activa = true AND o.cliente.id = :clienteId")
    List<Orden> findAllActiveByClienteId(@Param("clienteId") Long clienteId);

    List<Orden> findBySalidaTrueAndFechaSalidaBefore(@Param("fecha") LocalDate fecha);

    /**
     * Encuentra una Orden por su ID y carga de forma explícita la Recepción, ItemRecepcion, Cliente y Equipo
     * @param id ID de la orden
     * @return Orden con sus relaciones cargadas
     */
    @Query("SELECT DISTINCT o FROM Orden o "
           + "LEFT JOIN FETCH o.recepcion r "
           + "LEFT JOIN FETCH r.itemRecepcion "
           + "LEFT JOIN FETCH o.cliente "
           + "LEFT JOIN FETCH o.equipo "
           + "WHERE o.id = :id")
    Optional<Orden> findByIdWithRecepcion(@Param("id") Long id);
}
