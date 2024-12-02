package com.sso.app.entity.inspeccion;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoTransmision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ok;
    private Boolean picado;
    private Boolean desgastado;
    private Boolean roto;
    private String observacion;
}
