package com.sso.app.entity.inspeccion.pcpminig;


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
public class ItemPcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Eje Motriz
    private Boolean emOk;
    private Boolean emAlRod;
    private Boolean emAlRet;
    private Boolean emDiam;
    private Boolean emDef;
    private String emEsp;

    // Block Cabezal
    private Boolean bcOk;
    private Boolean bcAlRod;
    private Boolean bcAlRet;
    private Boolean bcDiam;
    private Boolean bcDef;
    private String bcEsp;

    // Placa Inferior
    private Boolean piOk;
    private Boolean piAlRod;
    private Boolean piAlRet;
    private Boolean piDiam;
    private Boolean piDef;
    private String piEsp;
}
