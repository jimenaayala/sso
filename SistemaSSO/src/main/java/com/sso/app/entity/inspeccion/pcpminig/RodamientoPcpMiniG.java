package com.sso.app.entity.inspeccion.pcpminig;
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
public class RodamientoPcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento axial29416;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSuperiorNJ217;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInferiorNJ214;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento zapata61822;
}
