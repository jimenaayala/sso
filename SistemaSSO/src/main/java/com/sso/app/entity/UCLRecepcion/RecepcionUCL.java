package com.sso.app.entity.UCLRecepcion;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sso.app.entity.Imagen;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "eliminado = false")
public class RecepcionUCL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "recepcionUCL", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes = new ArrayList<>();

    // 🔹 Relación unidireccional (RecepcionUCL → Item)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_recepcion_ucl_id")
    private ItemRecepcionUCL itemRecepcionUCL;

    private String comentario;

    private boolean eliminado = false;
}
