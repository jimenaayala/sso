package com.sso.app.entity.inspeccion.pcpcougarcd50;
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
public class TransmisionFrenoPcpCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // corona
    private Boolean crnOk;
    private Boolean crnPicado;
    private Boolean crnDesgastado;
    private Boolean crnRoto;
    private String crnEspecificar;

    // pinion
    private Boolean pnOk;
    private Boolean pnPicado;
    private Boolean pnDesgastado;
    private Boolean pnRoto;
    private String pnEspecificar;

    // RodJauAnti
    private Boolean rjaOk;
    private Boolean rjaPicado;
    private Boolean rjaDesgastado;
    private Boolean rjaRoto;
    private String rjaEspecificar;

}

