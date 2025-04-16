package com.sso.app.entity.inspeccion.pcpvh60;

import com.sso.app.entity.inspeccion.EstadoSistema;
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
public class SistemaHidraulicoPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bomba
    private Boolean bomOk;
    private Boolean bomFug;
    private Boolean bomRot;
    private Boolean bomEf;
    private String bomEsp;

    // Manifold
    private Boolean manOk;
    private Boolean manFug;
    private Boolean manRot;
    private Boolean manEf;
    private String manEsp;

    // Caliper
    private Boolean calOk;
    private Boolean calFug;
    private Boolean calRot;
    private Boolean calEf;
    private String calEsp;

    // Conjunto Mangueras
    private Boolean cmOk;
    private Boolean cmFug;
    private Boolean cmRot;
    private Boolean cmEf;
    private String cmEsp;
}
