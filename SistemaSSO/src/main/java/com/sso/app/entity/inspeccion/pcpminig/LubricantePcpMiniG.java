package com.sso.app.entity.inspeccion.pcpminig;
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

public class LubricantePcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lubricante Block Porta Rodamientos
    private Boolean lbprOk;
    private Boolean lbprPM;
    private Boolean lbprAgua;
    private Boolean lbprSucio;
    private String lbprEsp;
}
