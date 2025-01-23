package com.sso.app.entity;

import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo") // El tipo de clase se va a identificar mediante la propiedad "tipo"
@JsonSubTypes({
        @JsonSubTypes.Type(value = InspeccionPcpMiniG.class, name = "InspeccionPcpMiniG"),
        @JsonSubTypes.Type(value = InspeccionPcpCougar.class, name = "InspeccionPcpCougar"),
        @JsonSubTypes.Type(value = InspeccionPcpDV1.class, name = "InspeccionPcpDV1"),
        @JsonSubTypes.Type(value = InspeccionPcpVh60.class, name = "InspeccionPcpVh60")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private boolean eliminado=false;

}

