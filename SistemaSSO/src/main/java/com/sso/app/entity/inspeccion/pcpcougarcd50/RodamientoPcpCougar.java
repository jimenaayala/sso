package com.sso.app.entity.inspeccion.pcpcougarcd50;
import com.sso.app.entity.inspeccion.EstadoLubricante;
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
public class RodamientoPcpCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento cargaAxial;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSup;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInf;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento ejeSecSup;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento ejeSecInf;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento acoBomHidra;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento  torrBomHid;
}
