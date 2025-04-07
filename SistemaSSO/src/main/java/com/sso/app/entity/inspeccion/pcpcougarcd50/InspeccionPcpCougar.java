package com.sso.app.entity.inspeccion.pcpcougarcd50;
import com.sso.app.entity.inspeccion.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspeccionPcpCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private boolean eliminado=false;

    //EstadoLubricante
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteBlockRodamientos;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteSistemaFreno;
    //Item
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem ejeMotriz;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem ejeSecundario;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem blockCabezal;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaInterior;
    //rodamiento
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento cargaAxial;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSup;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInf;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento ejeSecSup;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento ejeSecInf;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento acoBomHidra;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento  torrBomHid;
    //transmisionFreno
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision corona;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pinion;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision RodJauAnti;

    //estadosistema
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema bomba;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema valvFren;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItemPolea polea;
}
