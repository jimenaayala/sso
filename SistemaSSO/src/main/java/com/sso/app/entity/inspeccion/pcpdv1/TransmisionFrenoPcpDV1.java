package com.sso.app.entity.inspeccion.pcpdv1;

import com.sso.app.entity.inspeccion.EstadoTransmision;
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
public class TransmisionFrenoPcpDV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision corona;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pinon;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision catraca;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision bolas;

}
