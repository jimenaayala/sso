package com.sso.app.entity.inspeccion;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ok;
    private Boolean alojamientoRodamiento;
    private Boolean alojamientoReten;
    private Double diametro;
    private Boolean deformado;
}