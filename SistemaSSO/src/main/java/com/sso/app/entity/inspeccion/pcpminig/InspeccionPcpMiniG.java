package com.sso.app.entity.inspeccion.pcpminig;
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
public class InspeccionPcpMiniG extends Inspeccion {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lubricantes_id")
    private LubricantePcpMiniG lubricantePcpMiniG = new LubricantePcpMiniG();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private ItemPcpMiniG itemPcpMiniG = new ItemPcpMiniG();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rodamientos_id")
    private RodamientoPcpMiniG rodamientoPcpMiniG =new RodamientoPcpMiniG();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_cubo_id")
    private ItemCuboPcpMiniG itemCuboPcpMiniG = new ItemCuboPcpMiniG();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sistema_hidraulico_id")
    private SistemaPcpMiniG sistemaHidraulicoPcpMiniG = new SistemaPcpMiniG();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polea_id")
    private ItemPolea poleaPcpMiniG = new ItemPolea();

}
