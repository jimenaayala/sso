package com.sso.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cuit;
    private String razonSocial;
    private String nombreFantasia;
    private String area;
    private String nombreContacto;
    private String telefono;
    private String mail;
    private boolean eliminado = false;
}
