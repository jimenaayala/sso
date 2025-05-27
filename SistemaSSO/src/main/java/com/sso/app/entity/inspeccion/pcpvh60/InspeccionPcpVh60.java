package com.sso.app.entity.inspeccion.pcpvh60;

import com.sso.app.entity.Imagen;
import com.sso.app.entity.inspeccion.ItemPolea;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspeccionPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private boolean eliminado=false;

    @OneToMany(mappedBy = "inspeccionPcpVh60", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenesVH60 = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lubricantes_id")
    private LubricantePcpVh60 lubricantePcpVh60 = new LubricantePcpVh60();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "items_id")
    private ItemPcpVh60 itemPcpVh60 = new ItemPcpVh60();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rodamientos_id")
    private RodamientoPcpVh60 rodamientoPcpVh60 = new RodamientoPcpVh60();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmision_freno_id")
    private TransmisionFrenoPcpVh60 transmisionFrenoPcpVh60 = new TransmisionFrenoPcpVh60();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sistema_hidraulico_id")
    private SistemaHidraulicoPcpVh60 sistemaHidraulicoPcpVh60 = new SistemaHidraulicoPcpVh60();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polea_id")
    private ItemPolea poleaPcpVh60 = new ItemPolea();
}


