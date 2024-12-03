package com.sso.app.entity.inspeccion.pcpvh60;

import com.sso.app.entity.inspeccion.EstadoRodamiento;
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
public class RodamientoPcpVh60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento axial294158;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSup6022;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInf6017;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento freno60051rsZ;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento antirretornoCsk25PpC3;
}
