package com.sso.app.entity.inspeccion;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoItemPolea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ok;
    private Boolean fisuras;
    private Boolean poros;
    private Double diametro;
    private Double trazabilidad;
    private String especificar;
}
