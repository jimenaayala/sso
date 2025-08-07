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
public class EnsayoDv1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ensayoDv1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenesDv1 = new ArrayList<>();

    // === EstadoEnsayo fields for each pressure ===
    // Presion 10
    private double presion10CurrentF;        // Frecuencia actual a presion 10
    private double presion10VoltajeSalida;   // U out a presion 10
    private double presion10CorrienteSalida; // I out en Amperios a presion 10
    private double presion10PosicionSalida1; // Posición Voltaje Salida 1 a presion 10
    private double presion10PosicionSalida2; // Posición Voltaje Salida 2 a presion 10
    private double presion10TorqueFrenado;
    private double presion10TorqueFrenado1;
    private double presion10TorqueFrenado2;
    private double presion10TorqueFabricaReferencia;
    private double presion10TorqueReferencia1;
    private double presion10TorqueReferencia2;
    private double presion10TemperaturaCarcazaC;

    // Presion 20
    private double presion20CurrentF;
    private double presion20VoltajeSalida;
    private double presion20CorrienteSalida;
    private double presion20PosicionSalida1;
    private double presion20PosicionSalida2;
    private double presion20TorqueFrenado;
    private double presion20TorqueFrenado1;
    private double presion20TorqueFrenado2;
    private double presion20TorqueFabricaReferencia;
    private double presion20TorqueReferencia1;
    private double presion20TorqueReferencia2;
    private double presion20TemperaturaCarcazaC;

    // Presion 70
    private double presion70CurrentF;
    private double presion70VoltajeSalida;
    private double presion70CorrienteSalida;
    private double presion70PosicionSalida1;
    private double presion70PosicionSalida2;
    private double presion70TorqueFrenado;
    private double presion70TorqueFrenado1;
    private double presion70TorqueFrenado2;
    private double presion70TorqueFabricaReferencia;
    private double presion70TorqueReferencia1;
    private double presion70TorqueReferencia2;
    private double presion70TemperaturaCarcazaC;

    // Presion 100
    private double presion100CurrentF;
    private double presion100VoltajeSalida;
    private double presion100CorrienteSalida;
    private double presion100PosicionSalida1;
    private double presion100PosicionSalida2;
    private double presion100TorqueFrenado;
    private double presion100TorqueFrenado1;
    private double presion100TorqueFrenado2;
    private double presion100TorqueFabricaReferencia;
    private double presion100TorqueReferencia1;
    private double presion100TorqueReferencia2;
    private double presion100TemperaturaCarcazaC;

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
