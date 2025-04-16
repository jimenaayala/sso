package com.sso.app.entity.inspeccion.pcpvh60;


import com.sso.app.entity.inspeccion.EstadoLubricante;
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
public class LubricantePcpVh60 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Lubricante Block Porta Rodamientos
    private Boolean lbprOk;
    private Boolean lbprPM;
    private Boolean lbprAgua;
    private Boolean lbprSucio;
    private String lbprEsp;

    // Lubricante Sistema Freno
    private Boolean lsfOk;
    private Boolean lsfPM;
    private Boolean lsfAgua;
    private Boolean lsfSucio;
    private String lsfEsp;

}
