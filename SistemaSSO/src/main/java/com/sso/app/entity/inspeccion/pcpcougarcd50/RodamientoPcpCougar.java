package com.sso.app.entity.inspeccion.pcpcougarcd50;
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
public class RodamientoPcpCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // cargaAxial
    private Boolean caOk;
    private Boolean caPicado;
    private Boolean caLaminado;
    private Boolean caFallaJaula;
    private Boolean caDesgaste;
    private String caEspecificar;

    // guiaSup
    private Boolean gsOk;
    private Boolean gsPicado;
    private Boolean gsLaminado;
    private Boolean gsFallaJaula;
    private Boolean gsDesgaste;
    private String gsEspecificar;

    // guiaInf
    private Boolean giOk;
    private Boolean giPicado;
    private Boolean giLaminado;
    private Boolean giFallaJaula;
    private Boolean giDesgaste;
    private String giEspecificar;

    // ejeSecSup
    private Boolean essOk;
    private Boolean essPicado;
    private Boolean essLaminado;
    private Boolean essFallaJaula;
    private Boolean essDesgaste;
    private String essEspecificar;

    // ejeSecInf
    private Boolean esiOk;
    private Boolean esiPicado;
    private Boolean esiLaminado;
    private Boolean esiFallaJaula;
    private Boolean esiDesgaste;
    private String esiEspecificar;

    // acoBomHidra
    private Boolean abhOk;
    private Boolean abhPicado;
    private Boolean abhLaminado;
    private Boolean abhFallaJaula;
    private Boolean abhDesgaste;
    private String abhEspecificar;

    // torrBomHid
    private Boolean tbhOk;
    private Boolean tbhPicado;
    private Boolean tbhLaminado;
    private Boolean tbhFallaJaula;
    private Boolean tbhDesgaste;
    private String tbhEspecificar;
}
