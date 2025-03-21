package com.sso.app.entity.inspeccion.pcpvh60;


import com.sso.app.entity.inspeccion.EstadoItem;
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
public class ItemPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem ejeMotriz;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem blockCabezal;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaInferior;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoItem placaSuperior;

}
