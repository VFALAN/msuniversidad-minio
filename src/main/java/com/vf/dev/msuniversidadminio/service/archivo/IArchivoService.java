package com.vf.dev.msuniversidadminio.service.archivo;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;

public interface IArchivoService {

    public ArchivoEntity findById(Integer pIdArchivo) throws MsUniversidadException;
    public ArchivoEntity save(ArchivoEntity pArchivoEntity);

}
