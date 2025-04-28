package com.sso.app.entity;

import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
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
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private boolean publicar;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "recepcion_id", nullable = true)
    private Recepcion recepcion;

    @ManyToOne
    @JoinColumn(name = "inspeccionvh60_id", nullable = true)
    private InspeccionPcpVh60 inspeccionPcpVh60;
    @ManyToOne
    @JoinColumn(name = "inspeccionminig_id", nullable = true)
    private InspeccionPcpMiniG inspeccionPcpMiniG;
    @ManyToOne
    @JoinColumn(name = "inspecciondv1_id", nullable = true)
    private InspeccionPcpDV1 inspeccionPcpDV1;
    @ManyToOne
    @JoinColumn(name = "inspeccioncougar_id", nullable = true)
    private InspeccionPcpCougar inspeccionPcpCougar;

    @ManyToOne
    @JoinColumn(name = "ensayo_id", nullable = true)
    private Ensayo ensayo;

    @ManyToOne
    @JoinColumn(name = "salida_id", nullable = true)
    private Salida salida;

}
