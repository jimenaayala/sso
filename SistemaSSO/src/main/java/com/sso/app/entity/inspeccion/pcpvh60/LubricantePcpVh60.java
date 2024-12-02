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

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteBlockPortaRodamientos;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoLubricante lubricanteSistemaFreno;
}
