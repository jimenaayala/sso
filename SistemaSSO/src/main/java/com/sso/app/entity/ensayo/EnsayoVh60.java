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
public class EnsayoVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ensayoVh60", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenesVh60 = new ArrayList<>();

    // RPM 100
    private double rpm100CurrentF;        // Frecuencia actual a 100 RPM
    private double rpm100VoltajeSalida;   // U out a 100 RPM
    private double rpm100CorrienteSalida; // I out en Amperios a 100 RPM
    private double rpm100PosicionSalida1; // Posición Voltaje Salida 1 a 100 RPM
    private double rpm100PosicionSalida2; // Posición Voltaje Salida 2 a 100 RPM
    private double rpm100TorqueFrenado;   // Torque de frenado a 100 RPM
    private double rpm100TorqueFrenado1;  // Torque de frenado 1 a 100 RPM
    private double rpm100TorqueFrenado2;  // Torque de frenado 2 a 100 RPM
    private double rpm100TorqueFabricaReferencia; // Torque fábrica ref a 100 RPM
    private double rpm100TorqueReferencia1; // Torque referencia 1 a 100 RPM
    private double rpm100TorqueReferencia2; // Torque referencia 2 a 100 RPM
    private double rpm100TemperaturaCarcazaC; // Temperatura carcaza en °C a 100 RPM

    // RPM 200
    private double rpm200CurrentF;        // Frecuencia actual a 200 RPM
    private double rpm200VoltajeSalida;   // U out a 200 RPM
    private double rpm200CorrienteSalida; // I out en Amperios a 200 RPM
    private double rpm200PosicionSalida1; // Posición Voltaje Salida 1 a 200 RPM
    private double rpm200PosicionSalida2; // Posición Voltaje Salida 2 a 200 RPM
    private double rpm200TorqueFrenado;   // Torque de frenado a 200 RPM
    private double rpm200TorqueFrenado1;  // Torque de frenado 1 a 200 RPM
    private double rpm200TorqueFrenado2;  // Torque de frenado 2 a 200 RPM
    private double rpm200TorqueFabricaReferencia; // Torque fábrica ref a 200 RPM
    private double rpm200TorqueReferencia1; // Torque referencia 1 a 200 RPM
    private double rpm200TorqueReferencia2; // Torque referencia 2 a 200 RPM
    private double rpm200TemperaturaCarcazaC; // Temperatura carcaza en °C a 200 RPM

    // RPM 300
    private double rpm300CurrentF;        // Frecuencia actual a 300 RPM
    private double rpm300VoltajeSalida;   // U out a 300 RPM
    private double rpm300CorrienteSalida; // I out en Amperios a 300 RPM
    private double rpm300PosicionSalida1; // Posición Voltaje Salida 1 a 300 RPM
    private double rpm300PosicionSalida2; // Posición Voltaje Salida 2 a 300 RPM
    private double rpm300TorqueFrenado;   // Torque de frenado a 300 RPM
    private double rpm300TorqueFrenado1;  // Torque de frenado 1 a 300 RPM
    private double rpm300TorqueFrenado2;  // Torque de frenado 2 a 300 RPM
    private double rpm300TorqueFabricaReferencia; // Torque fábrica ref a 300 RPM
    private double rpm300TorqueReferencia1; // Torque referencia 1 a 300 RPM
    private double rpm300TorqueReferencia2; // Torque referencia 2 a 300 RPM
    private double rpm300TemperaturaCarcazaC; // Temperatura carcaza en °C a 300 RPM

    // Carga Axial
    private boolean cargaAxialOK;
    private String cargaAxialObservacion;

    // Temperatura
    private boolean temperaturaOK;
    private String temperaturaObservacion;

    // Nivel de Ruido
    private boolean nivelDeRuidoOK;
    private String nivelDeRuidoObservacion;

    // Nivel de Vibración
    private boolean nivelDeVibracionOK;
    private String nivelDeVibracionObservacion;

    // Fuga de Aceite
    private boolean fugaDeAceiteOK;
    private String fugaDeAceiteObservacion;

    // Nivel de Aceite
    private boolean nivelDeAceiteOK;
    private String nivelDeAceiteObservacion;

    // Pintura
    private boolean pinturaOK;
    private String pinturaObservacion;
}