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

    // Corona
    private Boolean corOk;
    private Boolean corPic;
    private Boolean corDesg;
    private Boolean corRot;
    private String corEsp;

    // Piñón
    private Boolean pinOk;
    private Boolean pinPic;
    private Boolean pinDesg;
    private Boolean pinRot;
    private String pinEsp;

    // Pastillas de Freno
    private Boolean pfOk;
    private Boolean pfPic;
    private Boolean pfDesg;
    private Boolean pfRot;
    private String pfEsp;
}
