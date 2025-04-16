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

    // Axial 29416
    private Boolean axOk;
    private Boolean axPic;
    private Boolean axLam;
    private Boolean axFj;
    private Boolean axDesg;
    private String axEsp;

    // Guía Superior NJ217
    private Boolean gsOk;
    private Boolean gsPic;
    private Boolean gsLam;
    private Boolean gsFj;
    private Boolean gsDesg;
    private String gsEsp;

    // Guía Inferior NJ214
    private Boolean giOk;
    private Boolean giPic;
    private Boolean giLam;
    private Boolean giFj;
    private Boolean giDesg;
    private String giEsp;

    // Zapata 61822
    private Boolean zpOk;
    private Boolean zpPic;
    private Boolean zpLam;
    private Boolean zpFj;
    private Boolean zpDesg;
    private String zpEsp;
}
