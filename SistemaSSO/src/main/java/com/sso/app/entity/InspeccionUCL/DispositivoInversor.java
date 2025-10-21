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
public class DispositivoInversor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Pernos Extremo Cadena
    private String pernoExtCad;  //Observacion
    private Boolean pernoExtCadEND;// Aceptar / rechazar

    //Eje Pivot
    private String ejePivot;  //Insp de Roscas
    private Boolean ejePivotEND;

    //Ejes consetrico
    private String ejesConcentricos;  //Insp de Roscas
    private Boolean ejesConcentricosEND;

    //Ejes Excentricos
    private String ejesExcentricos;  //Insp de Roscas
    private Boolean ejesExcentricosEND;

    //Ruedas Metalicas
    private String ruedasMetalicasPestaña; //Pestaña en mm
    private int ruedasMetalicasCantOK;
    private int ruedasMetalicasCantRech;

    //Estructura(Nudo)
    private String estructuraInspVisual; //Inspeccion Visual
    private String estructuraObservaciones;

}
