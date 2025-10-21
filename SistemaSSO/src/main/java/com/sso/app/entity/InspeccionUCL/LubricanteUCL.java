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
public class LubricanteUCL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Lubricante Reductor
    private Boolean lubReductorOk;
    private Boolean lubReductorPM;
    private Boolean lubReductorAgua;
    private Boolean lubReductorSucio;
    private String lubReductorEsp;

    //Lubricante Rodamiento Rodillo

    private Boolean lubReductorRodOk;
    private Boolean lubReductorRodPM;
    private Boolean lubReductorRodAgua;
    private Boolean lubReductorRodSucio;
    private String lubReductorRodEsp;

    //Lubricante Dispositivo Inversor

    private Boolean lubDispInversorOk;
    private Boolean lubDispInversorPM;
    private Boolean lubDispInversorAgua;
    private Boolean lubDispInversorSucio;
    private String lubDispInversorEsp;

    //Lubricante Corona Superior

    private Boolean lubCoronaSuperiorOk;
    private Boolean lubCoronaSuperiorPM;
    private Boolean lubCoronaSuperiorAgua;
    private Boolean lubCoronaSuperiorSucio;
    private String lubCoronaSuperiorEsp;

    //Lubricante de Cadena y Transmision

    private Boolean lubCadenaTransmisionOk;
    private Boolean lubCadenaTransmisionPM;
    private Boolean lubCadenaTransmisionAgua;
    private Boolean lubCadenaTransmisionSucio;
    private String lubCadenaTransmisionEsp;

}
