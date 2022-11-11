package com.vf.dev.msuniversidadminio.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MsUniversidadException extends Exception {
    private String codigoInterno;

    public MsUniversidadException(String pMessage, String pCodigoInterno) {
        super(pMessage);
        this.setCodigoInterno(pCodigoInterno);
    }
}
