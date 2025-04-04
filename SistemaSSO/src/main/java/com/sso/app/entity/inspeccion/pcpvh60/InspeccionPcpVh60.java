package com.sso.app.entity.inspeccion.pcpvh60;

import com.sso.app.entity.inspeccion.*;
import jakarta.persistence.*;
import lombok.*;

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


//Bloque Lubricante
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteBlockPortaRodamientos;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteSistemaFreno;

//Bloque Item
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem ejeMotriz;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem blockCabezal;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaInferior;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaSuperior;

    //Bloque Rodamientos

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento axial294158;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSup6022;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInf6017;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento freno60051rsZ;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento antirretornoCsk25PpC3;

 //Bloque Transmisión de freno
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision corona;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pinion;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoTransmision pastillasFreno;

    //Bloque Sistema Hidraulico
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema bomba;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema manifold;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema caliper;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoSistema conjuntoMangueras;

    //Bloque Item Polea
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItemPolea polea;

}


