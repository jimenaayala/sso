package com.sso.app.entity.inspeccion.pcpdv1;

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
public class ItemPcpDV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Estado del eje motriz
    private Boolean emOk;
    private Boolean emAlRod;   // Alojamiento rodamiento
    private Boolean emAlRet;   // Alojamiento reten
    private Boolean emDiam;    // Diámetro
    private Boolean emDef;     // Deformado
    private String emEsp;      // Especificar

    // Estado del block cabezal
    private Boolean bcOk;
    private Boolean bcAlRod;
    private Boolean bcAlRet;
    private Boolean bcDiam;
    private Boolean bcDef;
    private String bcEsp;

    // Estado de la placa inferior
    private Boolean piOk;
    private Boolean piAlRod;
    private Boolean piAlRet;
    private Boolean piDiam;
    private Boolean piDef;
    private String piEsp;

    // Estado del protector de engranaje
    private Boolean peOk;
    private Boolean peAlRod;
    private Boolean peAlRet;
    private Boolean peDiam;
    private Boolean peDef;
    private String peEsp;
}
