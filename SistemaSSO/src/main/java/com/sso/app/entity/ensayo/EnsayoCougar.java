package com.sso.app.entity.ensayo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnsayoCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // === EstadoEnsayo fields for each RPM ===
    // RPM 100
    private double rpm100CurrentF;        // Frecuencia actual a 100 RPM
    private double rpm100VoltajeSalida;   // U out a 100 RPM
    private double rpm100CorrienteSalida; // I out en Amperios a 100 RPM
    private double rpm100PosicionSalida1; // Posición Voltaje Salida 1 a 100 RPM
    private double rpm100PosicionSalida2; // Posición Voltaje Salida 2 a 100 RPM
    private double rpm100TorqueFrenado;
    private double rpm100TorqueFrenado1;
    private double rpm100TorqueFrenado2;
    private double rpm100TorqueFabricaReferencia;
    private double rpm100TorqueReferencia1;
    private double rpm100TorqueReferencia2;
    private double rpm100TemperaturaCarcazaC;

    // RPM 200
    private double rpm200CurrentF;
    private double rpm200VoltajeSalida;
    private double rpm200CorrienteSalida;
    private double rpm200PosicionSalida1;
    private double rpm200PosicionSalida2;
    private double rpm200TorqueFrenado;
    private double rpm200TorqueFrenado1;
    private double rpm200TorqueFrenado2;
    private double rpm200TorqueFabricaReferencia;
    private double rpm200TorqueReferencia1;
    private double rpm200TorqueReferencia2;
    private double rpm200TemperaturaCarcazaC;

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

    private boolean pinturaOK;
    private String pinturaObservacion;
}
