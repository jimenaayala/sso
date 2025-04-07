package com.sso.app.entity.inspeccion.pcpdv1;
import com.sso.app.entity.inspeccion.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspeccionPcpDV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private boolean eliminado=false;

    //Lubricante
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteBlockPortaRodamientos;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteEngranajes;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteSistemaFreno;
    //Item
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem ejeMotriz;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem blockCabezal;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaInferior;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem protectorEngranaje;
    //Rodamiento
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento axial29415;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSup6212;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInf6212;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento corona6212Z;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento mHidr6210Z;
    //TransmisionFreno
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision corona;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pinon;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision catraca;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision bolas;
    //SistemaHidraulico
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema bomba;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema manifold;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema conjuntoMangueras;
    //ItemPolea
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItemPolea polea;


}
