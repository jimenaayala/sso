package com.sso.app.entity.inspeccion.pcpminig;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoItemCubo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ok;
    private Boolean picado;
    private Boolean desgastado;
    private Boolean desgasteChavetero;
    @Column(length = 500)
    private String observacion;
}
