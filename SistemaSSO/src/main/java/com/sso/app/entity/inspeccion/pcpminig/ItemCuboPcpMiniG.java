package com.sso.app.entity.inspeccion.pcpminig;
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
public class ItemCuboPcpMiniG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cubo Porta Rodamiento
    private Boolean cprOk;
    private Boolean cprPic;
    private Boolean cprDesg;
    private Boolean cprChav;
    private String cprObs;
}
