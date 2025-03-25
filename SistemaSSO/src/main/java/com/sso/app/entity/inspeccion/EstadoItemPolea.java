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
    private Boolean fisura;
    private Boolean poros;
    private Boolean disenoInadecuado;
    private Boolean numeroTrazabilidad;   //  Double??
    private String especificar;   //especificar
}
