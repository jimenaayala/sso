package com.sso.app.entity.inspeccion.pcpcougarcd50;
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
public class SistemaPcpCougar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // bomba
    private Boolean bombaOk;
    private Boolean bombaFugas;
    private Boolean bombaRoto;
    private Boolean bombaEficiencia;
    private String bombaEspecificar;

    // valvFren
    private Boolean valvOk;
    private Boolean valvFugas;
    private Boolean valvRoto;
    private Boolean valvEficiencia;
    private String valvEspecificar;
}
