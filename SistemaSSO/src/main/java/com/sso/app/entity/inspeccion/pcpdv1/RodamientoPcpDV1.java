package com.sso.app.entity.inspeccion.pcpdv1;
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
    // Rodamiento axial 29415
    private Boolean axOk;
    private Boolean axPic;    // Picado
    private Boolean axLam;    // Laminado
    private Boolean axFj;     // Falla en jaula
    private Boolean axDesg;   // Desgaste
    private String axEsp;

    // Rodamiento guía superior 6212
    private Boolean gsOk;
    private Boolean gsPic;
    private Boolean gsLam;
    private Boolean gsFj;
    private Boolean gsDesg;
    private String gsEsp;

    // Rodamiento guía inferior 6212
    private Boolean giOk;
    private Boolean giPic;
    private Boolean giLam;
    private Boolean giFj;
    private Boolean giDesg;
    private String giEsp;

    // Rodamiento de corona 6212Z
    private Boolean crOk;
    private Boolean crPic;
    private Boolean crLam;
    private Boolean crFj;
    private Boolean crDesg;
    private String crEsp;

    // Rodamiento m. hidráulico 6210Z
    private Boolean mhOk;
    private Boolean mhPic;
    private Boolean mhLam;
    private Boolean mhFj;
    private Boolean mhDesg;
    private String mhEsp;
}
