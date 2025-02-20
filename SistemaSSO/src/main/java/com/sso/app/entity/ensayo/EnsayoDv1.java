package com.sso.app.entity.ensayo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnsayoDv1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo presion10=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo presion20=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo presion70=new EstadoEnsayo();
    @OneToOne(cascade = CascadeType.ALL)
    private EstadoEnsayo presion100=new EstadoEnsayo();
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
