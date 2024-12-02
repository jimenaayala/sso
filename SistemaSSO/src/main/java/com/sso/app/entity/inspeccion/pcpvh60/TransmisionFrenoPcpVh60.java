package com.sso.app.entity.inspeccion.pcpvh60;

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
public class TransmisionFrenoPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision corona;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pinion;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pastillasFreno;
}
