package com.sso.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRecepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //cubreGrampa;
    private boolean cgestado;
    private String cgrequerimiento;
    private String cgobservacion;

    //cubrePolea;
    private boolean estado;
    private String requerimiento;
    private String observacion;

    //cubreVastago;
    private boolean cvestado;
    private String cvrequerimiento;
    private String cvobservacion;
    // grampaAntiEyeccion
    private boolean gaestado;
    private String garequerimiento;
    private String gaobservacion;

    // estructuraChasis
    private boolean ecestado;
    private String ecrequerimiento;
    private String ecobservacion;

    // linternaSeparador
    private boolean lsestado;
    private String lsrequerimiento;
    private String lsobservacion;

    // mesaDeMotor
    private boolean mmestado;
    private String mmrequerimiento;
    private String mmobservacion;

    // rielesDeMotor
    private boolean rmestado;
    private String rmrequerimiento;
    private String rmobservacion;

    // soporteDeTransporte
    private boolean stestado;
    private String strequerimiento;
    private String stobservacion;

    // poleaConducida
    private boolean pcestado;
    private String pcrequerimiento;
    private String pcobservacion;

}
