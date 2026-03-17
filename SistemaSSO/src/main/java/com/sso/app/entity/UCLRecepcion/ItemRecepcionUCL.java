package com.sso.app.entity.UCLRecepcion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRecepcionUCL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean tensoresEstado;
    private String tensoresRequerimiento;
    private String tensoresObservacion;

    private boolean balconFrontalEstado;
    private String balconFrontalRequerimiento;
    private String balconFrontalObservacion;

    private boolean balconSuperiorEstado;
    private String balconSuperiorRequerimiento;
    private String balconSuperiorObservacion;

    private boolean capotCubreRodilloEstado;
    private String capotCubreRodilloRequerimiento;
    private String capotCubreRodilloObservacion;

    private boolean cubrePoleasEstado;
    private String cubrePoleasRequerimiento;
    private String cubrePoleasObservacion;

    private boolean cubreDiscoEstado;
    private String cubreDiscoRequerimiento;
    private String cubreDiscoObservacion;

    private boolean soporteMotorReguladorEstado;
    private String soporteMotorReguladorRequerimiento;
    private String soporteMotorReguladorObservacion;

    private boolean crucetaEstroboEstado;
    private String crucetaEstroboRequerimiento;
    private String crucetaEstroboObservacion;

    private boolean placasContrapesosEstado;
    private String placasContrapesosRequerimiento;
    private String placasContrapesosObservacion;

    private boolean poleaConducidaEstado;
    private String poleaConducidaRequerimiento;
    private String poleaConducidaObservacion;

    private boolean speedSentryEstado;
    private String speedSentryRequerimiento;
    private String speedSentryObservacion;

    private String faltantesAdicionales;
}
