package com.sso.app.entity.inspeccion;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ok;
    private Boolean fuga;
    private Boolean roto;
    private Boolean eficiencia;
    private String especificar;

}
