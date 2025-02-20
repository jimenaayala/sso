package com.sso.app.entity.ensayo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoEnsayo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double currentF;        //
    private double voltajeSalida;  //U out
    private double corrienteSalida;  //I out en Amperios
    private double posicionSalida1;    //posicion Voltaje Salida 1 para Cougar
    private double posicionSalida2;    //posicion Voltaje Salida 2 para Cougar
    private double torqueFrenado;
    private double torqueFrenado1;
    private double torqueFrenado2;
    private double torqueFabricaReferencia;
    private double torqueReferencia1;
    private double torqueReferencia2;
    private double temperaturaCarcazaC;
}
