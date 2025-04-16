package com.sso.app.entity.inspeccion.pcpvh60;

import com.sso.app.entity.inspeccion.EstadoRodamiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RodamientoPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Axial 294158
    private Boolean axOk;
    private Boolean axPic;
    private Boolean axLam;
    private Boolean axFj;
    private Boolean axDesg;
    private String axEsp;

    // Guía Superior 6022
    private Boolean gsOk;
    private Boolean gsPic;
    private Boolean gsLam;
    private Boolean gsFj;
    private Boolean gsDesg;
    private String gsEsp;

    // Guía Inferior 6017
    private Boolean giOk;
    private Boolean giPic;
    private Boolean giLam;
    private Boolean giFj;
    private Boolean giDesg;
    private String giEsp;

    // Freno 6005-1RS-Z
    private Boolean frOk;
    private Boolean frPic;
    private Boolean frLam;
    private Boolean frFj;
    private Boolean frDesg;
    private String frEsp;

    // Antirretorno CSK25PP C3
    private Boolean arOk;
    private Boolean arPic;
    private Boolean arLam;
    private Boolean arFj;
    private Boolean arDesg;
    private String arEsp;
}
