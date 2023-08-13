package com.vf.dev.msuniversidadminio.model.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEvidenceDTO implements Serializable {

    @JsonProperty
    private String nombre;
    @JsonProperty
    private String path;
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String extencion;
    @JsonProperty
    private String downloadUrl;
    @JsonProperty
    private String tipoArchivo;
    @JsonProperty
    private Integer idTipoArchivo;
    @JsonProperty
    private String mineType;
}
