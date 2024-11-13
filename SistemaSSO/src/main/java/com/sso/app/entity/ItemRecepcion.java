package com.sso.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRecepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion cubreGrampa;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion cubrePolea;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion cubreVastago;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion grampaAntiEyeccion;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion estructuraChasis;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion linternaSeparador;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion mesaDeMotor;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion rielesDeMotor;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion soporteDeTransporte;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetailRecepcion poleaConducida;
}
