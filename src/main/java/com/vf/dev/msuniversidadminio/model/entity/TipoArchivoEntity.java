package com.vf.dev.msuniversidadminio.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "TIPO_ARCHIVO")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TipoArchivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ARCHIVO")
    private Integer idTipoArchivo;
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "DSECRIPCION")
    private String descripcion;

    @OneToMany(mappedBy = "idTipoArchivo")
    private List<ArchivoEntity> archivosList;
}
