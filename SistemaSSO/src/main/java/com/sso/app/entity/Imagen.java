package com.sso.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.entity.ensayo.EnsayoVh60;
import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDv1;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String descripcion;
    private boolean publicar;

    @ManyToOne
    @JoinColumn(name = "recepcion_id")
    @JsonIgnore
    private Recepcion recepcion;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpVh60_id")
    @JsonIgnore
    private InspeccionPcpVh60 inspeccionPcpVh60;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpMiniG_id")
    @JsonIgnore
    private InspeccionPcpMiniG inspeccionPcpMiniG;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpCougar_id")
    @JsonIgnore
    private InspeccionPcpCougar inspeccionPcpCougar;

    @ManyToOne
    @JoinColumn(name = "inspeccionPcpDv1_id")
    @JsonIgnore
    private InspeccionPcpDv1 inspeccionPcpDv1;

    @ManyToOne
    @JoinColumn(name = "ensayoCougar_id")
    @JsonIgnore
    private EnsayoCougar ensayoCougar;

    @ManyToOne
    @JoinColumn(name = "ensayoDv1_id")
    @JsonIgnore
    private EnsayoDv1 ensayoDv1;

    @ManyToOne
    @JoinColumn(name = "ensayoMiniG_id")
    @JsonIgnore
    private EnsayoMiniG ensayoMiniG;

    @ManyToOne
    @JoinColumn(name = "ensayoVh60_id")
    @JsonIgnore
    private EnsayoVh60 ensayoVh60;

}
