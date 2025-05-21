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
public class SistemaHidraulicoPcpDv1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Bomba
    private Boolean bmOk;
    private Boolean bmFug;    // Fugas
    private Boolean bmRot;
    private Boolean bmEf;     // Eficiencia
    private String bmEsp;

    // Manifold
    private Boolean mfOk;
    private Boolean mfFug;
    private Boolean mfRot;
    private Boolean mfEf;
    private String mfEsp;

    // Conjunto de mangueras
    private Boolean cmOk;
    private Boolean cmFug;
    private Boolean cmRot;
    private Boolean cmEf;
    private String cmEsp;
}
