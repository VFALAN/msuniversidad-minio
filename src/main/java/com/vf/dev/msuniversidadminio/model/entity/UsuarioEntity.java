package com.vf.dev.msuniversidadminio.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIOS")
@Data
public class UsuarioEntity extends GenericTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonProperty
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @JsonProperty
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @JsonProperty
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @JsonProperty
    @Column(name = "NOMBRE")
    private String nombre;
    @JsonProperty
    @Column(name = "CORREO")
    private String correo;
    @JsonProperty
    @Column(name = "FOLIO")
    private String folio;
    @JsonProperty
    @Column(name = "MATRICULA")
    private String matricula;
    @JsonProperty
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @JsonProperty
    @Column(name = "PASSWORD")
    private String password;
    @JsonProperty
    @Column(name = "CURP")
    private String curp;
    @JsonProperty
    @Column(name = "DES_GENERO")
    private String desGenero;
    @JsonProperty
    @Column(name = "GENERO")
    private int genero;
    @JsonProperty
    @Column(name = "EDAD")
    private int edad;
    @JsonProperty
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;
    @JsonIgnore
    @Column(name = "ID_PERFIL")
    private Integer perfil;
    @JsonIgnore
    @Column(name = "ID_ESTATUS")
    private Integer estatus;
    @JsonIgnore
    @Column(name = "ID_DIRECCION")
    private Integer idDireccion;

    @OneToMany(mappedBy = "usuario")
    private List<ArchivoEntity> archivos;
}
