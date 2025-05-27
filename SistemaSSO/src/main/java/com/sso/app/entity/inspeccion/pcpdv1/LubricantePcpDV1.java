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
public class LubricantePcpDV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lubricante del block porta rodamientos
    private Boolean lbprOk;
    private Boolean lbprPM;      // Partículas metálicas
    private Boolean lbprAgua;
    private Boolean lbprSucio;
    private String lbprEsp;

    // Lubricante de engranajes
    private Boolean lbeOk;
    private Boolean lbePM;
    private Boolean lbeAgua;
    private Boolean lbeSucio;
    private String lbeEsp;

    // Lubricante del sistema de freno
    private Boolean lbfOk;
    private Boolean lbfPM;
    private Boolean lbfAgua;
    private Boolean lbfSucio;
    private String lbfEsp;
}
