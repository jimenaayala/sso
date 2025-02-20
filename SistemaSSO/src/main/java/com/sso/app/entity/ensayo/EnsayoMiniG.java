package com.sso.app.entity.ensayo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnsayoMiniG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo rpm200=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo rpm300=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo rpm400=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo rpm500=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion cargaAxial;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion temperatura;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion nivelDeRuido;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion nivelDeVibracion;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion fugaDeAceite;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion nivelDeAceite;
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoRectificacion pintura;
}
