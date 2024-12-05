package com.sso.app.entity.inspeccion.pcpminig;
import com.sso.app.entity.inspeccion.ItemPolea;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspeccionPcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private boolean eliminado=false;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "lubricantes_id")
//    private LubricantePcpMiniG lubricante;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "item_id")
//    private ItemPcpMiniG item;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "rodamientos_id")
//    private RodamientoPcpMiniG rodamiento;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "item_cubo_id")
//    private ItemCuboPcpMiniG itemCubo;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "sistema_hidraulico_id")
//    private SistemaPcpMiniG sistemaHidraulico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polea_id")
    private ItemPolea polea;

}
