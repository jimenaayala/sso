package com.sso.app.entity.inspeccion.pcpdv1;
import com.sso.app.entity.Inspeccion;
import com.sso.app.entity.inspeccion.ItemPolea;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspeccionPcpDV1 extends Inspeccion {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lubricantes_id")
    private LubricantePcpDV1 lubricantePcpDV1 = new LubricantePcpDV1();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private ItemPcpDV1 itemPcpDV1 = new ItemPcpDV1();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rodamientos_id")
    private RodamientoPcpDV1 rodamientoPcpDV1 = new RodamientoPcpDV1();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmision_freno_id")
    private TransmisionFrenoPcpDV1 transmisionFrenoPcpDv1 = new TransmisionFrenoPcpDV1();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sistema_hidraulico_id")
    private SistemaHidraulicoPcpDv1 sistemaHidraulicoPcpDV1 =new SistemaHidraulicoPcpDv1();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polea_id")
    private ItemPolea poleaPcpDV1 = new ItemPolea();

}
