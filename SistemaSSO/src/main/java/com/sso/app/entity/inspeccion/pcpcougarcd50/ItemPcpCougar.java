package com.sso.app.entity.inspeccion.pcpcougarcd50;
import com.sso.app.entity.inspeccion.EstadoItem;
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
public class ItemPcpCougar {
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

    //Eje Seciundario
    private Boolean esOk;
    private Boolean esAlRod;
    private Boolean esAlRet;
    private Boolean esDiam;
    private Boolean esDef;
    private String esEsp;

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


    //agregar ejeMotriz, ejeSecundario, BlockCabezal, PlacaInferior
}
