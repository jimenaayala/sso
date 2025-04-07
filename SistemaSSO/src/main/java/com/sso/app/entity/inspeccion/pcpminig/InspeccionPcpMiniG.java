package com.sso.app.entity.inspeccion.pcpminig;
import com.sso.app.entity.inspeccion.*;
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

    //Estado Lubricante
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteBlockPortaRodamientos;
    //Item
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem ejeMotriz;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem blockCabezal;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaInferior;
    //Rodamiento
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento axial29416;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSuperiorNJ217;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInferiorNJ214;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento zapata61822;
    //ItemCubo
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItemCubo cuboPortaRodamiento;
    //Sistema
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema zapataDeFreno;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema ferodo;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema levaS;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema vastagoDeResortes;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema resortes;
    //ItemPolea
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItemPolea polea;

}
