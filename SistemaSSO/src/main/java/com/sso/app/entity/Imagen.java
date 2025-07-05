package com.sso.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
}
