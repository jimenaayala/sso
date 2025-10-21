package com.sso.app.entity.InspeccionUCL;

import com.sso.app.entity.inspeccion.pcpvh60.LubricantePcpVh60;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspeccionUCL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private boolean eliminado=false;

    //CONECTAR IMAGENES

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lubricantesUCL_id")
    private LubricanteUCL lubricanteUCL = new LubricanteUCL();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dispositivoInversor_id")
    private DispositivoInversor dispositivoInversor = new DispositivoInversor();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmisionUCL_id")
    private TransmisionUCL transmisionUCL = new TransmisionUCL();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estructuraUCL_id")
    private EstructuraUCL estructuraUCL = new EstructuraUCL();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sistemaSeguridad_id")
    private SistemaSeguridad sistemaSeguridad = new SistemaSeguridad();

}
