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

    // Corona
    private Boolean crnOk;
    private Boolean crnPic;
    private Boolean crnDesg;  // Desgastado
    private Boolean crnRot;
    private String crnEsp;

    // Piñón
    private Boolean pnOk;
    private Boolean pnPic;
    private Boolean pnDesg;
    private Boolean pnRot;
    private String pnEsp;

    // Catraca
    private Boolean ctOk;
    private Boolean ctPic;
    private Boolean ctDesg;
    private Boolean ctRot;
    private String ctEsp;

    // Bolas
    private Boolean blOk;
    private Boolean blPic;
    private Boolean blDesg;
    private Boolean blRot;
    private String blEsp;

}
