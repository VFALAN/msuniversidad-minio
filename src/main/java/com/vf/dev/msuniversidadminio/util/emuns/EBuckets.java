package com.vf.dev.msuniversidadminio.util.emuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EBuckets {
    BUCKET_REPORTES("reportes",1),
    BUCKET_FORMATOS("formatos",2),
    BUCKETS_INFORMACION_PERSONAL("informacionpersonal",3),
    BUCKET_TAREAS("tareas",4);


    private String name;
    private Integer id;
}
