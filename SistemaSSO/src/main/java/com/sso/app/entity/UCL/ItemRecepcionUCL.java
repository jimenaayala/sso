package com.sso.app.entity.UCL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    // Tensores
    private boolean tensoresEstado;
    private String tensoresRequerimiento;
    private String tensoresObservacion;

    // Balcón frontal
    private boolean balconFrontalEstado;
    private String balconFrontalRequerimiento;
    private String balconFrontalObservacion;

    // Balcón superior
    private boolean balconSuperiorEstado;
    private String balconSuperiorRequerimiento;
    private String balconSuperiorObservacion;

    // Capot cubre rodillo
    private boolean capotCubreRodilloEstado;
    private String capotCubreRodilloRequerimiento;
    private String capotCubreRodilloObservacion;

    // Cubre poleas
    private boolean cubrePoleasEstado;
    private String cubrePoleasRequerimiento;
    private String cubrePoleasObservacion;

    // Cubre disco
    private boolean cubreDiscoEstado;
    private String cubreDiscoRequerimiento;
    private String cubreDiscoObservacion;

    // Soporte motor / Regulador
    private boolean soporteMotorReguladorEstado;
    private String soporteMotorReguladorRequerimiento;
    private String soporteMotorReguladorObservacion;

    // Cruceta / Estrobo
    private boolean crucetaEstroboEstado;
    private String crucetaEstroboRequerimiento;
    private String crucetaEstroboObservacion;

    // Placas contrapesos
    private boolean placasContrapesosEstado;
    private String placasContrapesosRequerimiento;
    private String placasContrapesosObservacion;

    // Polea conducida
    private boolean poleaConducidaEstado;
    private String poleaConducidaRequerimiento;
    private String poleaConducidaObservacion;

    // Speed Sentry
    private boolean speedSentryEstado;
    private String speedSentryRequerimiento;
    private String speedSentryObservacion;

    // Faltantes adicionales
    private String faltantesAdicionales;
}
