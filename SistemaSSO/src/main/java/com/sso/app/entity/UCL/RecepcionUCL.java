package com.sso.app.entity.UCL;

import com.sso.app.entity.Imagen;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecepcionUCL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "recepcionUCL", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ItemRecepcionUCL itemRecepcionUCL= new ItemRecepcionUCL();

    private String comentario;
    private boolean eliminado=false;
}
