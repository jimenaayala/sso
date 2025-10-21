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
public class TransmisionUCL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observacionesReductor;
    private Boolean ReductorEND; //Acept/Rechaza

    private String observacionesCadena;
    private Boolean CadenaEND;

    private String observacionesRodillo;
    private Boolean RodilloEND;

    private String observacionesCoronaConducida;
    private Boolean CoronaConducidaEND;

    private String observacionesCoronaConductora;
    private Boolean CoronaConductoraEND;

    private String observacionesBandaCarga;
    private Boolean BandaCargaEND;
}
