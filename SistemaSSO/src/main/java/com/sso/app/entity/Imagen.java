package com.sso.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private boolean publicar;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "recepcion_id", nullable = true)
    private Recepcion recepcion;

    @ManyToOne
    @JoinColumn(name = "inspeccion_id", nullable = true)
    private Inspeccion inspeccion;

    @ManyToOne
    @JoinColumn(name = "ensayo_id", nullable = true)
    private Ensayo ensayo;

    @ManyToOne
    @JoinColumn(name = "salida_id", nullable = true)
    private Salida salida;

}
