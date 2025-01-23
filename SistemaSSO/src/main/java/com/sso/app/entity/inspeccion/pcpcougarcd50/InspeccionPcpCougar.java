package com.sso.app.entity.inspeccion.pcpcougarcd50;
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
public class InspeccionPcpCougar extends Inspeccion {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lubricantes_id")
    private LubricantePcpCougar lubricantePcpCoguar = new LubricantePcpCougar();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "items_id")
    private ItemPcpCougar itemPcpCoguar = new ItemPcpCougar();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rodamientos_id")
    private RodamientoPcpCougar rodamientoPcpCoguar = new RodamientoPcpCougar();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmision_freno_id")
    private TransmisionFrenoPcpCougar transmisionFrenoPcpCoguar = new TransmisionFrenoPcpCougar();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sistema_hidraulico_id")
    private SistemaPcpCougar sistemaHidraulicoPcpCoguar = new SistemaPcpCougar();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polea_id")
    private ItemPolea poleaPcpCoguar = new ItemPolea();

}
