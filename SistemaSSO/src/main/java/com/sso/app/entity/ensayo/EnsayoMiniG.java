package com.sso.app.entity.ensayo;

import com.sso.app.entity.Imagen;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnsayoMiniG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ensayoMiniG", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenesMiniG = new ArrayList<>();

    // === EstadoEnsayo fields for each RPM ===
    // RPM 200
    private double rpm200CurrentF;        // Frecuencia actual a 200 RPM
    private double rpm200VoltajeSalida;   // U out a 200 RPM
    private double rpm200CorrienteSalida; // I out en Amperios a 200 RPM
    private double rpm200PosicionSalida1; // Posición Voltaje Salida 1 a 200 RPM
    private double rpm200PosicionSalida2; // Posición Voltaje Salida 2 a 200 RPM
    private double rpm200TorqueFrenado;
    private double rpm200TorqueFrenado1;
    private double rpm200TorqueFrenado2;
    private double rpm200TorqueFabricaReferencia;
    private double rpm200TorqueReferencia1;
    private double rpm200TorqueReferencia2;
    private double rpm200TemperaturaCarcazaC;

    // RPM 300
    private double rpm300CurrentF;
    private double rpm300VoltajeSalida;
    private double rpm300CorrienteSalida;
    private double rpm300PosicionSalida1;
    private double rpm300PosicionSalida2;
    private double rpm300TorqueFrenado;
    private double rpm300TorqueFrenado1;
    private double rpm300TorqueFrenado2;
    private double rpm300TorqueFabricaReferencia;
    private double rpm300TorqueReferencia1;
    private double rpm300TorqueReferencia2;
    private double rpm300TemperaturaCarcazaC;

    // RPM 400
    private double rpm400CurrentF;
    private double rpm400VoltajeSalida;
    private double rpm400CorrienteSalida;
    private double rpm400PosicionSalida1;
    private double rpm400PosicionSalida2;
    private double rpm400TorqueFrenado;
    private double rpm400TorqueFrenado1;
    private double rpm400TorqueFrenado2;
    private double rpm400TorqueFabricaReferencia;
    private double rpm400TorqueReferencia1;
    private double rpm400TorqueReferencia2;
    private double rpm400TemperaturaCarcazaC;

    // RPM 500
    private double rpm500CurrentF;
    private double rpm500VoltajeSalida;
    private double rpm500CorrienteSalida;
    private double rpm500PosicionSalida1;
    private double rpm500PosicionSalida2;
    private double rpm500TorqueFrenado;
    private double rpm500TorqueFrenado1;
    private double rpm500TorqueFrenado2;
    private double rpm500TorqueFabricaReferencia;
    private double rpm500TorqueReferencia1;
    private double rpm500TorqueReferencia2;
    private double rpm500TemperaturaCarcazaC;

    // === EstadoRectificacion fields ===
    private boolean cargaAxialOK;
    private String cargaAxialObservacion;

    private boolean temperaturaOK;
    private String temperaturaObservacion;

    private boolean nivelDeRuidoOK;
    private String nivelDeRuidoObservacion;

    private boolean nivelDeVibracionOK;
    private String nivelDeVibracionObservacion;

    private boolean fugaDeAceiteOK;
    private String fugaDeAceiteObservacion;

    private boolean nivelDeAceiteOK;
    private String nivelDeAceiteObservacion;

    private boolean pinturaOK;
    private String pinturaObservacion;
}
