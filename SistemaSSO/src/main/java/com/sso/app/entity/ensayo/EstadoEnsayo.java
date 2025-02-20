package com.sso.app.entity.ensayo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private double currentF;
    private double uOut;
    private double iOut;
    private double iOutPos1;
    private double iOutPos2;
    private double torqueFrenado;
    private double torqueFrenado1;
    private double torqueFrenado2;
    private double torqueFabricaReferencia;
    private double torqueReferencia1;
    private double torqueReferencia2;
    private double temperaturaCarcazaC;
}
