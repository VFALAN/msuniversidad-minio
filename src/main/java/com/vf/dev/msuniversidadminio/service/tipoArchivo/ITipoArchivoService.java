package com.vf.dev.msuniversidadminio.service.tipoArchivo;

import com.vf.dev.msuniversidadminio.model.entity.TipoArchivoEntity;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;

public interface ITipoArchivoService {
    TipoArchivoEntity findById(Integer pTipoArchivo) throws MsUniversidadException;
}
