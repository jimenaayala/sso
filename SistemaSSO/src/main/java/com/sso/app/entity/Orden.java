package com.sso.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fecha;
    private String comentario;
    private String docTransporte;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
