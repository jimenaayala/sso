package com.sso.app.entity.inspeccion.pcpminig;
import com.sso.app.entity.inspeccion.EstadoSistema;
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
public class SistemaPcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Zapata de Freno
    private Boolean zfOk;
    private Boolean zfFug;
    private Boolean zfRot;
    private Boolean zfEf;
    private String zfEsp;

    // Ferodo
    private Boolean ferOk;
    private Boolean ferFug;
    private Boolean ferRot;
    private Boolean ferEf;
    private String ferEsp;

    // Leva S
    private Boolean levOk;
    private Boolean levFug;
    private Boolean levRot;
    private Boolean levEf;
    private String levEsp;

    // Vástago de Resortes
    private Boolean vrOk;
    private Boolean vrFug;
    private Boolean vrRot;
    private Boolean vrEf;
    private String vrEsp;

    // Resortes
    private Boolean resOk;
    private Boolean resFug;
    private Boolean resRot;
    private Boolean resEf;
    private String resEsp;
}
