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
public class SistemaSeguridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String obsDiscoCaliper;
    private Boolean discoCaliperEND;

    private String obsActuador;
    private Boolean actuadorEND;

    private String obsCableadoSensores;
    private Boolean cableadoSensoresEND;

}
