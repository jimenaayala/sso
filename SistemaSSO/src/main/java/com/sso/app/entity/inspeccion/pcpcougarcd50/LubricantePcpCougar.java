package com.sso.app.entity.inspeccion.pcpcougarcd50;
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
public class LubricantePcpCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // lubricanteBlockRodamientos
    private Boolean lbrOk;
    private Boolean lbrPM;
    private Boolean lbrAgua;
    private Boolean lbrSucio;
    private String lbrEsp;

    // lubricanteSistemaFreno
    private Boolean lsfOk;
    private Boolean lsfPM;
    private Boolean lsfAgua;
    private Boolean lsfSucio;
    private String lsfEsp;
}
