package com.sso.app.entity.InspeccionUCL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstructuraUCL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String obserEstrucGral;
    private Boolean EstrucGralEND;

    private String obserTelescopica;
    private Boolean TelescopicaEND;

    private String obserRuedasPlasticas;
    private int ruedasPlasticasCantOK;
    private int ruedasPlasticasCantRech;

    private String obserCajaContrapeso;
    private Boolean cajaContrapesoEND;
}
