package com.sso.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroOT;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha;
    private String comentario;
    private String remitoTransporte;
    private String etapaActual;
    private boolean activa = true;
    private boolean eliminado = false;

    //proximo dos atributos para el borrado de imagenes
    private boolean salida = false;
    private LocalDate fechaSalida;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "inspeccion_id")
    private Inspeccion inspeccion;

    @ManyToOne
    @JoinColumn(name = "recepcion_id")
    private Recepcion recepcion;
    @ManyToOne
    @JoinColumn(name = "ensayo_id")
    private Ensayo ensayo;
    @ManyToOne
    @JoinColumn(name = "salida_id")
    private Salida salidaEtapa;

    
}
