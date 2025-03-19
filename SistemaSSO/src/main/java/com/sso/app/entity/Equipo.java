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
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numSerieEquipo;
    private boolean eliminado=false;
    private String marca;
    @ManyToOne
    @JoinColumn(name = "tipoequipo_id")
    private TipoEquipo tipoEquipo;

}
