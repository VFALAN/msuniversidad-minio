package com.vf.dev.msuniversidadminio.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "ARCHIVOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArchivoEntity extends GenericTable implements Serializable {
    @Id
    @Column(name = "ID_ARCHIVO")
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArchivo;
    @Column(name="EXTENCION_ARCHIVO")
    private String extencionArchivo;
    @Column(name = "TIPO_ARCHIVO")
    private String tipoArchivo;
    @Column(name="BUCKET")
    private String bucket;
    @Column(name="DESCRIPCION")
    private String descripcion;
    @Column(name="NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Column(name="RUTA")
    private String ruta;
    @ManyToOne
    @JoinColumn(name="ID_USUARIO")
    private UsuarioEntity usuario;
}
