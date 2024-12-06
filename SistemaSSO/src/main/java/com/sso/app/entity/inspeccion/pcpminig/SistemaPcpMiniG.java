package com.sso.app.entity.inspeccion.pcpminig;
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
public class SistemaPcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema zapataDeFreno;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema ferodo;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema levaS;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema vastagoDeResortes;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema resortes;
}
