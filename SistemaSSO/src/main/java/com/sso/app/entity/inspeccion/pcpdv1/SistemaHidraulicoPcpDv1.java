package com.sso.app.entity.inspeccion.pcpdv1;
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
public class SistemaHidraulicoPcpDv1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema bomba;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema manifold;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema conjuntoMangueras;
}
