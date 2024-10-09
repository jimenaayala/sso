package com.sso.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @OneToOne
    @JoinColumn(name = "inspeccion_id")
    private Inspeccion inspeccion;

    @OneToOne
    @JoinColumn(name = "recepcion_id")
    private Recepcion recepcion;
    @OneToOne
    @JoinColumn(name = "ensayo_id")
    private Ensayo ensayo;
    @OneToOne
    @JoinColumn(name = "salida_id")
    private Salida salida;

    
}
