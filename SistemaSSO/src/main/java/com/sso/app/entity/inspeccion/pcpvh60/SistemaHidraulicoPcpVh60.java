package com.sso.app.entity.inspeccion.pcpvh60;

import com.sso.app.entity.inspeccion.EstadoSistema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SistemaHidraulicoPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema bomba;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema manifold;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema caliper;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema conjuntoMangueras;

}
