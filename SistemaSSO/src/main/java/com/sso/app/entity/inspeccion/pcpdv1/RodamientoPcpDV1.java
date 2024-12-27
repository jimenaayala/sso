package com.sso.app.entity.inspeccion.pcpdv1;
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
public class RodamientoPcpDV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento axial29415;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaSup6212;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento guiaInf6212;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento corona6212Z;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRodamiento mHidr6210Z;
}
