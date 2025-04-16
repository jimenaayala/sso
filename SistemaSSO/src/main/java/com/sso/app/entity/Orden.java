package com.sso.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.entity.ensayo.EnsayoVh60;
import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroOT;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha;
    private String comentario;
    private String remitoTransporte;
    private String etapaActual;
    private boolean activa = true;
    private boolean eliminado = false;

    //proximo dos atributos para el borrado de imagenes
    private boolean salida = false;
    private LocalDate fechaSalida;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;



    @ManyToOne
    @JoinColumn(name = "inspeccionPcpVh60_id")
    private InspeccionPcpVh60 inspeccionPcpVh60;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpMiniG_id")
    private InspeccionPcpMiniG inspeccionPcpMiniG;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpDV1_id")
    private InspeccionPcpDV1 inspeccionPcpDV1;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpCougar_id")
    private InspeccionPcpCougar inspeccionPcpCougar;

//    @ManyToOne
//    @JoinColumn(name = "recepcion_id")
//    private Recepcion recepcion;

    //@ManyToOne
    //@JoinColumn(name = "ensayo_id")
    //private Ensayo ensayo;

//    @ManyToOne
//    @JoinColumn(name = "ensayoVH60_id")
//    private EnsayoVh60 ensayoVh60;
//
//    @ManyToOne
//    @JoinColumn(name = "ensayoMiniG_id")
//    private EnsayoMiniG ensayoMiniG;
//
//    @ManyToOne
//    @JoinColumn(name = "ensayoDv1_id")
//    private EnsayoDv1 ensayoDv1;

//    @ManyToOne
//    @JoinColumn(name = "ensayoCougar_id")
//    private EnsayoCougar ensayoCougar;
//
//    @ManyToOne
//    @JoinColumn(name = "salida_id")
//    private Salida salidaEtapa;



    
}
